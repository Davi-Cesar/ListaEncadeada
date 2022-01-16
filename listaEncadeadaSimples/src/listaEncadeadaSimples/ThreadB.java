package listaEncadeadaSimples;


public class ThreadB extends Thread {
	
	private ListaEncadeada lista;
	@SuppressWarnings("unused")
	private String name;
	private int value;
	

	public ThreadB(String name, ListaEncadeada lista, int value) {
		super();
		this.value = value;
		this.name = name;
		this.lista = lista;
		
	}
	
	public void run() { 
		lista.buscarValue(value);
	}

}
