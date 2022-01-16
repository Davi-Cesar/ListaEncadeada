package listaEncadeadaSimples;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.Semaphore;

public class ListaEncadeada {

	private int capacidade;
	private static LinkedList<Integer> lista;
	
	Semaphore sem = new Semaphore(1, true);
	public ListaEncadeada(int capacidade) {	
		 this.capacidade = capacidade;
		 lista = new LinkedList<Integer>();
	}
	
	public synchronized int retornaTamanho() {
		return lista.size();
	}
	
	public synchronized void inserirValue(int value) {
		while(lista.size() == capacidade) {
			System.out.println("Lista está cheia!");
			System.out.println(Thread.currentThread().getName() +  " Suspensa. \n");
			try {			
				wait();				
			}  catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {			
			sem.acquire();				
			lista.add(value);
			System.out.println(Thread.currentThread().getName() +  " Inserindo "  + value);
		}  catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sem.release();
		}
		
		notify();
	} 
	
	public synchronized void removerValue(int value) { 
		while(lista.size() == 0) {
			System.out.println("Lista vazia!");
			System.out.println( Thread.currentThread().getName() +  "Suspensa.\n");
			try {			
				wait();				
			}  catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}try {			
			sem.acquire();				
			ListIterator<Integer> iterador = lista.listIterator(0);
			while(iterador.hasNext()){  
				Integer i = iterador.next(); 
				if( i == value) {
					lista.remove(i);
				} 
			}
			System.out.println( Thread.currentThread().getName() +  " Removendo " + value);	
		}  catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sem.release();
		}
		notify();
	}
	
	public synchronized void buscarValue(int value) { 
		ListIterator<Integer> iterador = lista.listIterator(0);
		while(iterador.hasNext()){  
		     Integer i = iterador.next(); 
		     if( i == value) {
		    	 System.out.println( Thread.currentThread().getName() +  " Buscando " + value + " index " + iterador.nextIndex());	
		     } 
		}
	}
	
	public synchronized static void mostrarLista() {
		ListIterator<Integer> iterador = lista.listIterator(0);
		System.out.print("[ "); 
	    while(iterador.hasNext()){  
	      Integer i = iterador.next(); 
	      System.out.print(i + " "); 
	    }
	    System.out.println("]"); 
	}

}
