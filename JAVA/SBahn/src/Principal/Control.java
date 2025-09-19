package Principal;

public class Control {
	
	private boolean estacionOcupada;
	private int contadorTrenes;
	public boolean alarma;
	
	public Control() {
		this.estacionOcupada = false;
		this.alarma = false;
	}
	
	public synchronized void solicitarAcceso(Maniobra maniobra) throws InterruptedException {
		
		System.out.println("Llega un nuevo tren");
	
		while(alarma || estacionOcupada) {
			
			System.out.println("esperando acceso");
			wait();
			
		}
		
		maniobra.setEstado("ACCESO_AUTORIZADO");
		
		System.out.println("$$$$$$$$$$Tren entra en estacion");
		
		estacionOcupada = true;
		
		notifyAll();
		
	}
	
	public synchronized void salirEstacion(Maniobra maniobra) throws InterruptedException {
		
		while(alarma) {
			wait();
		}
		
		System.out.println("Salida autorizada saliendo estacion");
		
		maniobra.setEstado("SALIDA_AUTORIZADA");
		
		estacionOcupada = false;
		
		notifyAll();
		
	}
	
	public synchronized void activarAlarma() {
		
		Maniobra maniobra = new Maniobra();
		
		alarma = true;
		
		maniobra.setEstado("ALARMA");
		
		notifyAll();
		
	
	}
	
	
	
	
	

}
