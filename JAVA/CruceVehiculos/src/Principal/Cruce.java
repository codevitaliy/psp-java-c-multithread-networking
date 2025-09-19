package Principal;

public class Cruce {

	private int colaDerecha;
	private int colaIzquierda;
	private int colaAmbulancias;
	private boolean cruceBloqueado;

	public Cruce() {
		colaDerecha = 0;
		colaIzquierda = 0;
		colaAmbulancias = 0;
		cruceBloqueado = false;
	}
	
	
	public synchronized void entrarCruce (Tipo tipo) throws InterruptedException {
		
		switch(tipo) {
		
		case VEHICULO_IZQ:
			this.colaIzquierda++;
		break;
		
		case VEHICULO_DCHA:
			this.colaDerecha++;
		break;
		
		case AMBULANCIA:
			this.colaAmbulancias++;
		}
		
		
		while(cruceBloqueado 
				|| (tipo == Tipo.VEHICULO_IZQ && colaAmbulancias > 0)
				|| (tipo == Tipo.VEHICULO_DCHA && colaAmbulancias > 0)
				|| (tipo == Tipo.VEHICULO_DCHA && colaIzquierda > colaDerecha)
				|| (tipo == Tipo.VEHICULO_IZQ && colaDerecha > colaIzquierda)
				)
		{
			wait();
		}
		
switch(tipo) {
		
		case VEHICULO_IZQ:
			this.cruceBloqueado = true;
			colaIzquierda--;
		break;
		
		case VEHICULO_DCHA:
			this.cruceBloqueado = true;
			colaDerecha--;
			
		break;
			
		case AMBULANCIA:
			this.cruceBloqueado = true;
			colaAmbulancias--;
		break;
			
			
		}
	}
	
	public synchronized void salirCruce(Tipo tipo) {
switch(tipo) {
		
		case VEHICULO_IZQ:
			this.cruceBloqueado = false;
			notifyAll();
		break;
		
		case VEHICULO_DCHA:
			this.cruceBloqueado = false;
			notifyAll();
			
		break;
			
		case AMBULANCIA:
			this.cruceBloqueado = false;
			
			notifyAll();
		break;
			
			
		}
	}



}
