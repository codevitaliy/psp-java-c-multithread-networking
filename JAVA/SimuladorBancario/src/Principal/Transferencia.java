package Principal;

enum Tipo {
	INGRESAR, RETIRAR
}

public class Transferencia extends Thread{
	
	private Tipo tipo;
	private int cantidad;
	private GestorBanco gestorBanco;
	
	public Transferencia (GestorBanco gestorBanco,Tipo tipo, int cantidad) {
	
	this.gestorBanco = gestorBanco;
	this.tipo = tipo;
	this.cantidad = cantidad;
	}
	
	@Override
	public void run() {
		
		
		switch (tipo) {
		case INGRESAR:
			
			try {
				gestorBanco.ingresar(this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case RETIRAR:
			
			try {
				gestorBanco.retirar(this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;

		default:
			
			System.out.println("error switch default");
			
			break;
		}
		
		
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	
	
	
	
}
