package Principal;

public class Gimnasio {

	private int colaAbonadosA;
	private int colaAbonadosB;
	private static final int N_MAX = 30;
	private int nAbonados;
	private boolean esAlterna;
	private int porcentajeLibre;
	private double recaudacion;
	private boolean alarma;

	public Gimnasio() {
		this.colaAbonadosA = 0;
		this.colaAbonadosB = 0;
		this.nAbonados = 0;
		this.esAlterna = false;
		this.porcentajeLibre = 100;
		this.alarma = false;

	}

	public synchronized void abonadoEnAccesoA(int id) throws InterruptedException {

		colaAbonadosA++;

		System.out.println("Entra id: " + id + " cola A " + colaAbonadosA);

		while (nAbonados >= N_MAX || alarma) {
			
			System.out.println("LLENO ESPERNADO id: " + id + " cola A " + colaAbonadosA);
			wait();
		}
		
		actualizarRestricciones();
		
		while(esAlterna && colaAbonadosB > colaAbonadosB ) {
			
			System.out.println("Abonado id: " + id + "esperando, COLA ALTERNA" );
			actualizarRestricciones();
			wait();
		}
		
		colaAbonadosA--;

		nAbonados++;

		System.out.println("Entra A y Entrenando id: " + id + "Numero abonados: " + nAbonados);

		notifyAll();

	}

	public synchronized void abonadoEnAccesoB(int id) throws InterruptedException {

		colaAbonadosB++;

		System.out.println("Entra id: " + id + " cola B " + colaAbonadosA);
		

		while (nAbonados >= N_MAX || alarma ) {
			System.out.println("LLENO ESPERNADO id: " + id + " cola A " + colaAbonadosA);
			wait();
		}
		
while(esAlterna && colaAbonadosA > colaAbonadosB ) {
			
			System.out.println("Abonado id: " + id + "esperando, COLA ALTERNA" );
			actualizarRestricciones();
			wait();
		}
		
		

		colaAbonadosB--;
		
		nAbonados++;
		
		System.out.println("Entra B y Entrenando id: " + "Numero abonados: " + nAbonados);

		notifyAll();

	}

	public synchronized void abonadoAbandonaGimnasio(int id) {

		nAbonados--;

		System.out.println("Abandona cliente: " + id);

		notifyAll();

	}

	public synchronized void actualizarRestricciones() {

		this.porcentajeLibre = 100 - ((nAbonados * 100) / N_MAX);

		if (porcentajeLibre < 20) {
			this.esAlterna = true;
		} 
		
		this.esAlterna = false;

	}

	public synchronized double abonaEstancia(int tiempo) {

		double pago;

		pago = tiempo * 0.20;

		recaudacion += pago;

		System.out.println("Paga:" + pago + "Total recaudacion: " + recaudacion);

		return pago;

	}
	
	public synchronized void activarAlarma() {
		
		this.alarma = true;
		
	}
	
	public synchronized void desactivarAlarma() {
		
		this.alarma = false;
		
	}
	
	public synchronized boolean estadoAlarma() {
		return this.alarma;
	}

}
