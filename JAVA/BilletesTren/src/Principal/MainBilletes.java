package Principal;

import java.util.ArrayList;

public class MainBilletes {
	
	public static void main(String[] args) throws InterruptedException {
		
		GestorBilletes gestor = new GestorBilletes();
		
		ArrayList<Cliente> arrayClientes = new ArrayList<>();
		
		for(int i = 0; i < 30 ; i++) {
			
			arrayClientes.add(new Cliente(gestor, i));
			
		}
		
for(int i = 0; i < arrayClientes.size() ; i++) {
			
			arrayClientes.get(i).start();
			
		}

for(int i = 0; i < arrayClientes.size() ; i++) {
	
	arrayClientes.get(i).join();
	
}

System.out.println("finaliza el main");


		
	}
}
