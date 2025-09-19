package Principal;

public class Cliente extends Thread {
	
	private int idCliente;

	private GestorClientes gestorClientes;

	
	public Cliente(int idCliente, GestorClientes gestorClientes) {
		this.idCliente = idCliente;
		this.gestorClientes = gestorClientes;
		
	}
	

	@Override
	public void run() {
	    try {
	        boolean lograEntrar = gestorClientes.entrar(this);

	        if (lograEntrar) {
	            System.out.println("Cliente " + idCliente + " ha entrado en la tienda.");

	            boolean compra = gestorClientes.comprarProducto();

	            if (compra) {
	                System.out.println("Cliente " + idCliente + " ha comprado un producto.");
	            } else {
	                System.out.println("Cliente " + idCliente + " no ha podido comprar (agotado).");
	            }

	            gestorClientes.salir();
	            System.out.println("Cliente " + idCliente + " ha salido de la tienda.");
	        } else {
	            System.out.println("Cliente " + idCliente + " se va tras 10 intentos fallidos.");
	        }

	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

		
		
	
	
	public int getClienteId() {
		return idCliente;
	}
	
	

}
