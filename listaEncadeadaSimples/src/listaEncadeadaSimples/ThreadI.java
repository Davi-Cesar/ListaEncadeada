package listaEncadeadaSimples;

import java.util.concurrent.Semaphore;

public class ThreadI extends Thread  {
	

	private int value;
	private ListaEncadeada lista;
	private Semaphore semaforo;
	
	public ThreadI(String name, ListaEncadeada lista, int value, Semaphore semaphore) {
		super();
		this.value = value;
		this.lista = lista;
		this.semaforo = semaphore;
	}
	
	@Override
	public void run() { 
		try {
			semaforo.acquire();
			Thread.sleep(500);
			lista.inserirValue(value);
			ListaEncadeada.mostrarLista();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}
}
