package listaEncadeadaSimples;

import java.util.concurrent.Semaphore;

public class ThreadR extends Thread {

	private int value;
	private ListaEncadeada lista;
	private Semaphore semaforo;
	public ThreadR(String name, ListaEncadeada lista, int value, Semaphore semaphore)  {
		super();
		this.value = value;
		this.lista = lista;
		this.semaforo = semaphore;
	}

	public void run() { 
		try {
			semaforo.acquire();
			lista.removerValue(value);
			ListaEncadeada.mostrarLista();	
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}
}
	
