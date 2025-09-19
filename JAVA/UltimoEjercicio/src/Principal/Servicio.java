package Principal;

import java.util.ArrayList;

public class Servicio {
	
	private ArrayList<Peticion> peticiones;
	private int contadorVip;
	
	
	public Servicio () {
		
		this.peticiones = new ArrayList<>();
		this.contadorVip = 0;
	}
	
	
	
	public synchronized void registarPeticion(Peticion peticion) throws InterruptedException {
		
		boolean esVip = false;
		
		if(peticion.getId().equals("VIP")) {
			
			esVip = true;
			contadorVip++;
			
		}
		
		System.out.println("Se registra nueva peticion:");
		
		peticiones.add(peticion);
		
		while(peticion.getRespuesta().equals("") || !esVip && contadorVip > 0) {
			
			System.out.println("Peticion: " + peticion.getId() + " esperando respuesta...");
			
			wait();
			
		}
		
		if(esVip) {
			contadorVip--;
		}
		
		peticiones.remove(peticion);
		
		System.out.println("Peticion " + peticion.getId() + " siendo atendida");
		
		notifyAll();
		
	}
	
	
	public synchronized Peticion atenderPeticion(String id, String mensaje) throws InterruptedException {
		
		
		
		for(Peticion peticion : peticiones) {
			
			if(peticion.getId().equals(id)) {
				
				peticion.setRespuesta(mensaje);
				notifyAll();
				
				return peticion;
				
	
			}
	
		}
		return null;
	}
	
	public synchronized void atenderPeticionLocalidad(Peticion p) {
		
		for(Peticion peticion : peticiones) {
			
			if(peticion.getLocalidad().equals(p.getLocalidad()) && !p.getRespuesta().equals("")) {
				
				peticion.setRespuesta(p.getRespuesta());
				
				notifyAll();
				
			}
			
		}

	}
	
	public synchronized String listadoPeticionesPtes() {
		
		String listado = "";
		
		for(Peticion peticion: peticiones) {
			
			listado += peticion.toString() + "\n";
			
		}
		
		return listado;
		

	}
	
	
	
	
	
	
	
	

}
