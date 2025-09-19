package Principal;

import java.util.ArrayList;

public class Central {
	private ArrayList<Turno> peticiones;
	private ArrayList<Turno> funcionarios;

	public Central() {
		peticiones = new ArrayList<>();
		funcionarios = new ArrayList<>();
	}

	// Método sincronizado para agregar un turno a la lista correspondiente
	public synchronized void agregar(Turno turno) {

		int tipo = turno.getTipo();

		switch (tipo) {
		case 0:
			peticiones.add(turno);
			break;
		case 1:
			funcionarios.add(turno);
			break;
		default:
			break;
		}

		System.out.println("Ciudadanos: " + peticiones.size() + ", Funcionarios: " + funcionarios.size());

		notifyAll();
	}

	public synchronized void hacerPeticion() throws InterruptedException {

		while (peticiones.size() == 0 || funcionarios.size() == 0) {
			wait();
		}

		Turno peticion;
		Turno funcionario;

		for (Turno p : peticiones) {
			peticion = p;

			for (Turno f : funcionarios) {
				funcionario = f;

				if (p.getConcepto().equals(funcionario.getConcepto())) {
					System.out.println("Emparejamiento realizado");

					peticiones.remove(peticion);
					funcionarios.remove(funcionario);

					System.out.println("Ciudadanos: " + peticiones.size() + ", Funcionarios: " + funcionarios.size());
					notifyAll();
					return;
				}
			}
		}

		System.out.println("No se pudo emparejar");
		System.out.println("Ciudadanos: " + peticiones.size() + ", Funcionarios: " + funcionarios.size());
		notifyAll();
	}
	
	
	public synchronized void atender(Turno turno) throws InterruptedException {
		
		funcionarios.add(turno);
		
		boolean esperar = false;;
		
		for(Turno peticion : peticiones) {
			
			if(!peticion.getConcepto().equals(turno.getConcepto())) {
		
				esperar = true;	
			}
		}
		
		while(esperar) {
			
			for(Turno peticion : peticiones) {
				
				if(!peticion.getConcepto().equals(turno.getConcepto())) {
					
					esperar = false;
					break;
				}
				
				if(esperar) {
					wait();
				}
				
			}
		}
		
		Turno peticionAtender = null;
		
for(Turno peticion : peticiones) {
			
			if(!peticion.getConcepto().equals(turno.getConcepto())) {
		
				peticionAtender = peticion;
				break;
			}
		}

		peticiones.remove(peticionAtender);
		
		
		
		notifyAll();
		
	}
	
	
	
	
}







