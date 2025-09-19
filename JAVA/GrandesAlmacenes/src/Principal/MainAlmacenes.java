package Principal;

public class MainAlmacenes {
 public static void main(String[] args) throws InterruptedException {
	 
	 GestorClientes gestorClientes = new GestorClientes();
	 
	 Cliente[] clientes = new Cliente[300];
	 
	 
	 System.out.println("EMPIEZAN LAS REBAJAS");
	 
   for (int i = 0; i < clientes.length; i++) {
		
  	 clientes[i] = new Cliente(i, gestorClientes);
  	 
  	 clientes[i].start();
	}
   
   for (int i = 0; i < clientes.length; i++) {
		clientes[i].join();
	}
	
	 
	 
	 
	 
	 
 }
}
