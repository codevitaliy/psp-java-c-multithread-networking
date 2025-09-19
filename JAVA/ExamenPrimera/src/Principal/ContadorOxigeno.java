package Principal;

public class ContadorOxigeno extends Thread {
	
	private int nMedidas;
	private int nMedidasInferior;
	private Gimnasio gimnasio;
	
	
	public ContadorOxigeno(Gimnasio gimnasio) {
		
		this.gimnasio = gimnasio;
		nMedidas = 0;
		nMedidasInferior = 0;
	}
	
	@Override
	
	public void run() {
		
		boolean fin = false;
		
		while(!fin) {

			try {
				analizarMedidas();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
	
	
	
	public synchronized void analizarMedidas() throws InterruptedException {
		
		
		Thread.sleep(5000);
		
		int contadorOxigeno = (int)(Math.random() * 200 + 100);
		
		if(contadorOxigeno < 143) {
			
			nMedidasInferior++;
			
			if(nMedidasInferior >= 3) {
				
				gimnasio.activarAlarma();
				
			}
			
		}else if(contadorOxigeno > 143) {
			
			
			nMedidas++;
			
			if(nMedidas >= 6) {
				
				gimnasio.desactivarAlarma();
				
			}	
		}
	}
}
