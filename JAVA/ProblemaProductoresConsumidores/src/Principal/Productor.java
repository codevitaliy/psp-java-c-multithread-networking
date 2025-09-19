package Principal;

public class Productor extends Thread {
	
	private GestorHilos gestor;
	private int idProductor;
	private int cantidad;
	
	public Productor(GestorHilos gestor,int idProductor) {
		
		this.idProductor = idProductor;
		this.gestor = gestor;
		this.cantidad = (int)(Math.random() * 10 + 1);
		
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep((int)(Math.random() * 500));
			gestor.producir(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public int getIdProductor() {
		return this.idProductor;
	}
	

}
