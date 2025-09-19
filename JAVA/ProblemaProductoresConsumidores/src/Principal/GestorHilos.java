package Principal;

import java.util.ArrayList;

public class GestorHilos {
	
	private ArrayList<Integer> pila;
	private int numMaxPila;
	private boolean pilaBloqueada;
	
	public GestorHilos() {
		this.pila = new ArrayList<>();
		this.numMaxPila = 3;
		this.pilaBloqueada = false;
	}
	
	public synchronized void producir(Productor productor) throws InterruptedException {
		
		while(pilaBloqueada || pila.size() == numMaxPila) {
			System.out.println("Productor " + productor.getIdProductor() + " espera. Pila llena o bloqueada.");
			wait();
		}
		
		pilaBloqueada = true;
		
		int numRandom = (int)(Math.random() * 10 + 1);
		pila.add(numRandom);

		System.out.println("Entra productor: " + productor.getIdProductor() + " ha producido: " + numRandom + " -> Pila actual: " + pila);
		
		//Thread.sleep((int)(Math.random() * 500));
		
		pilaBloqueada = false;
		notifyAll();
	}
	
	public synchronized void consumir(Consumidor consumidor) throws InterruptedException {
		
		while(pilaBloqueada || pila.size() == 0) {
			System.out.println("Consumidor " + consumidor.getIdConsumidor() + " espera. Pila vacía o bloqueada.");
			wait();
		}
		
		pilaBloqueada = true;
		
		System.out.println("Entra consumidor: " + consumidor.getIdConsumidor() + " -> Pila antes de consumir: " + pila);
		
		int sumaTotal = 0;
		for (Integer p : pila) {
			sumaTotal += p;
		}
		
		pila.clear();
		pila.add(sumaTotal);
		
		System.out.println("Consumidor " + consumidor.getIdConsumidor() + " ha consumido y ha dejado la pila con: " + pila);
		
		//Thread.sleep((int)(Math.random() * 500));
		
		pilaBloqueada = false;
		notifyAll();
	}
	
	public synchronized boolean comprobarArrayList() {
		
		return pila.size() > 1;
		
	}
	
}
