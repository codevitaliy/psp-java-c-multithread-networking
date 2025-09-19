package Principal;

import java.util.ArrayList;

public class Servicio {

	private ArrayList<Peticion> peticiones;
	private int contadorVip;

	public Servicio() {
		this.peticiones = new ArrayList<>();
		this.contadorVip = 0;
	}

	// ------------------------------------------------PETICION CLIENTE
	// REMOTO--------------------------------------------

	public synchronized void registrarPeticion(Peticion peticion) throws InterruptedException {

		boolean soyVip = false;

		if (peticion.getId().equals("VIP")) {

			soyVip = true;
			contadorVip++;

		}

		peticiones.add(peticion);

		System.out.println("Petición recibida:");
		System.out.println(peticion.toString());

		while (peticion.getRespuesta().equals("") || (!soyVip && contadorVip > 0)) { // 
			System.out.println("Peticion id: " + peticion.getId() + " esperando respuesta...");
			wait();
		}

		
		 if(soyVip) { contadorVip--; }
		 

		peticiones.remove(peticion);

		notifyAll();

	}

//----------------------------------------------------HILO OPERADOR INVOCA ---------------------------------------

	public synchronized Peticion atenderPeticion(String id, String respuesta) throws InterruptedException {

		for (Peticion peticion : peticiones) {

			if (peticion.getId().equals(id)) {

				peticion.setRespuesta("ENVIAR" + " " + id + " " + respuesta);

				notifyAll();

				return peticion;

			}
		}

		return null;

	}

	public synchronized String listadoPeticionesPtes() {

		String listado = "";

		for (Peticion peticion : peticiones) {

			listado += peticion.toString() + "\n";

		}

		return listado;

	}

	public synchronized void atenderPeticionLocalida(Peticion peticion, String respuesta) {

		for (Peticion p : peticiones) {

			if (peticion.getLocalidad().equals(p.getLocalidad())) {

				p.setRespuesta("ENVIAR" + " " + p.getId() + " " + respuesta);

				notifyAll();

			}

		}

	}

}