package listaEncadeadaSimples;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		int count = 15;
		Semaphore semaphore = new Semaphore(2);
		ListaEncadeada lista = new ListaEncadeada(10);
		for(int i = 10 ; i <= count ; i++ ) {
			
			ThreadI threadI = new ThreadI("Inserir" + i, lista, i, semaphore);
			threadI.start(); // Inserir Elementos na lista
			try {
				threadI.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
;
		}
		
			ThreadR threadR = new ThreadR("Remover", lista, 15, semaphore);

			ThreadB threadB = new ThreadB("Buscar", lista, 15);		
			
			threadB.start(); // Burcar Elemento da lista 
			threadR.start(); // Remover Elementos na lista

			try {
				
				threadB.join();
				threadR.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			

		}


		
		
	
	
}
