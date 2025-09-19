package Principal;

import java.util.ArrayList;

public class MainProblema {

	public static void main(String[] args) throws InterruptedException {

		GestorHilos gestor = new GestorHilos();

		ArrayList<Productor> productores = new ArrayList<>();
		ArrayList<Consumidor> consumidores = new ArrayList<>();

		for (int i = 0; i < 30; i++) {
			
	
				productores.add(new Productor(gestor, i));

				consumidores.add(new Consumidor(gestor, i));

		}

		for (Productor productor : productores) {
			productor.start();
		}

		for (Consumidor consumidor : consumidores) {
			consumidor.start();
		}

		for (Productor productor : productores) {
			try {
				productor.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (Consumidor consumidor : consumidores) {
			try {
				consumidor.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Consumidor consumidor = new Consumidor(gestor,999);
		
		if(gestor.comprobarArrayList()) {
			
			
			consumidor.start();
			System.out.println("ultimo hilo consumidor creado para consumir lo que queda");
		}
		
		consumidor.join();
		
		
		System.out.println("fin main");
	}

}
