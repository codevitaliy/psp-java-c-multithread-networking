package Principal;

import java.util.ArrayList;

public class HelpDesk {

	private ArrayList<Trabajo> clientes;
	private ArrayList<Trabajo> tecnicos;

	public synchronized void agregarTrabajos(Trabajo trabajo) {

		if (trabajo.getTipo() == 0) {
			clientes.add(trabajo);
			System.out.println("Cliente agregado: " + trabajo);

		} else if (trabajo.getTipo() == 1) {
			tecnicos.add(trabajo);
			System.out.println("Tecnico agregado: " + tecnicos);
		} else {
			System.out.println("error metodo agregarTrabajos");
		}
		
		notifyAll();

	}

	public synchronized void buscarCliente(Trabajo tecnico) throws InterruptedException {
		// METODO PARA LOS TECNICOS
		
		while(clientes.size() == 0) {
			System.out.println("Tecnico esperando, no hay tecnicos " + tecnicos.size());
			wait();
		}
		
		//tecnicos.remove(tecnico);
		
		
		
	}

	public synchronized void buscarTecnico(Trabajo cliente) throws InterruptedException {
		// METODO PARA LOS CLIENTES
		
		while(tecnicos.isEmpty()) {
			
			System.out.println("Cliente esperando, no hay tecnicos " + tecnicos.size());
			wait();
			
		}
		
		
		
	}
	

}
