package Principal;

public class Consumidor extends Thread {
	
	private GestorHilos gestor;
	private int idConsumidor;
	
	public Consumidor(GestorHilos gestor,int idConsumidor) {
		
		this.gestor = gestor;
		this.idConsumidor = idConsumidor;
		
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep((int)(Math.random() * 500));
			gestor.consumir(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public int getIdConsumidor() {
		return idConsumidor;
	}
	
	

}
