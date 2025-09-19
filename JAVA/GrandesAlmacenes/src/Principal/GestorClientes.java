package Principal;

public class GestorClientes {
	
	private int nProductos;
	private int aforoActual;
	private int capacidadMaxima;;
	
	
	public GestorClientes() {
		this.nProductos = 100;
		this.aforoActual = 0;
		this.capacidadMaxima = 1;
		
	}
	
	
	
	public synchronized boolean entrar(Cliente cliente) throws InterruptedException {
		
		int intentos = 0;
		
		while(aforoActual == capacidadMaxima  && intentos < 10) {
			intentos++;
			System.out.println("Cliente:" + cliente.getClienteId() + " Intentos: " + intentos);
			wait();
		}
		
		if (intentos == 10) { //Si abandonó la espera por superar el número de intentos
			return false; // No ha logrado entrar
		}
		
		aforoActual++;
		return true; 
		
	}
	
	public synchronized boolean comprarProducto() throws InterruptedException {
		
		if(nProductos > 0) {
			nProductos--;
			return true;
		}
		
		return false;
		
	}
	
	public synchronized void salir() {
		aforoActual--;
		notifyAll();
		
	}
	
	
	

}
