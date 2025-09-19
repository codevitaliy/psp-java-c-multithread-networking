package Principal;

enum Tipo {
	ENTRADA_A,
	ENTRADA_B
}

public class Abonado extends Thread {
	
	private int idAbonado;
	private Tipo tipo;
	private Gimnasio gimnasio;
	
	public Abonado(int idAbonado, Gimnasio gimnasio) {
		
		this.idAbonado = idAbonado;
		
		int nRandom = (int)(Math.random() * 10 + 1);
		
		if(nRandom > 5) {
			this.tipo = Tipo.ENTRADA_A;
		}else {
			this.tipo = Tipo.ENTRADA_B;
		}
		
		this.gimnasio = gimnasio;
		
	}
	
	
	@Override
	public void run() {
		
		Tipo tipo= getTipoAbonado();
		
		switch (tipo) {
		
		case ENTRADA_A:
			try {
				

				gimnasio.abonadoEnAccesoA(getIdAbonado());
				int tiempoEntrenado = (int)(Math.random()* 5 + 1);
				Thread.sleep(tiempoEntrenado * 1000);
				gimnasio.abonaEstancia(tiempoEntrenado);
				gimnasio.abonadoAbandonaGimnasio(getIdAbonado());
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case ENTRADA_B:
			try {
				gimnasio.abonadoEnAccesoB(getIdAbonado());
				int tiempoEntrenado = (int)(Math.random()* 5 + 1);
				Thread.sleep(tiempoEntrenado * 1000);
				gimnasio.abonaEstancia(tiempoEntrenado);
				gimnasio.abonadoAbandonaGimnasio(getIdAbonado());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
		
		
	}
	
	
	
	public int getIdAbonado() {
		return this.idAbonado;
	}
	
	public Tipo getTipoAbonado() {
		return this.tipo;
	}
	
	
	
	
	

}
