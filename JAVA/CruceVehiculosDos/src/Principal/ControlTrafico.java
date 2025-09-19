package Principal;

import java.util.ArrayList;

public class ControlTrafico {

	ArrayList<Mensaje> vehiculosIzquierda;
	ArrayList<Mensaje> vehiculosDerecha;
	ArrayList<Mensaje> ambulancias;
	private int contadorAmbulancias;
	private int contadorVehiculos;

	public ControlTrafico() {

		this.vehiculosIzquierda = new ArrayList<>();
		this.vehiculosDerecha = new ArrayList<>();
		this.ambulancias = new ArrayList<>();
		this.contadorVehiculos = 0;
		this.contadorAmbulancias = 0;

	}

	public synchronized void registrarVehiculo(Mensaje mensaje) throws InterruptedException {

		Tipo tipo = mensaje.getTipo();
		boolean esAmbulancia = false;

		switch (tipo) {

		case VEHICULO_IZQ:
			vehiculosIzquierda.add(mensaje);
			break;
		case VEHICULO_DCHA:
			vehiculosDerecha.add(mensaje);
			break;
		case AMBULANCIA:
			ambulancias.add(mensaje);
			esAmbulancia = true;
		}

		while (tipo == Tipo.AMBULANCIA && contadorVehiculos > 0 
				|| mensaje.getTipo() == Tipo.VEHICULO_DCHA && contadorVehiculos > 0
				|| mensaje.getTipo() == Tipo.VEHICULO_IZQ && contadorVehiculos > 0
				|| (!esAmbulancia && contadorAmbulancias > 0) 
		    || (mensaje.getTipo() == Tipo.VEHICULO_DCHA && vehiculosIzquierda.size() > vehiculosDerecha.size())
		    || (mensaje.getTipo() == Tipo.VEHICULO_IZQ && vehiculosDerecha.size() > vehiculosIzquierda.size())) {

			wait();
		}

		switch (tipo) {

		case VEHICULO_IZQ:
			vehiculosIzquierda.remove(mensaje);
			break;
		case VEHICULO_DCHA:
			vehiculosDerecha.remove(mensaje);
			break;
		case AMBULANCIA:
			ambulancias.remove(mensaje);
			esAmbulancia = false;
			contadorAmbulancias--;
		}

		notifyAll();

	}

	public synchronized void entraVehiculo(Mensaje mensaje) {

		Tipo tipo = mensaje.getTipo();

		switch (tipo) {

		case VEHICULO_IZQ:
			mensaje.setTexto("Acceso Autorizado");
			this.contadorVehiculos++;
			break;
		case VEHICULO_DCHA:
			mensaje.setTexto("Acceso Autorizado");
			this.contadorVehiculos++;
			break;
		case AMBULANCIA:
			this.contadorAmbulancias++;
			mensaje.setTexto("Acceso Autorizado");
		break;
		}
		

	}
	
	public synchronized void salirCruce(Mensaje mensaje) {

		
		Tipo tipo = mensaje.getTipo();

		switch (tipo) {

		case VEHICULO_IZQ:
			mensaje.setTexto("Acceso Autorizado");
			this.contadorVehiculos--;
			break;
		case VEHICULO_DCHA:
			mensaje.setTexto("Acceso Autorizado");
			this.contadorVehiculos--;
			break;
		case AMBULANCIA:
			this.contadorAmbulancias--;
			mensaje.setTexto("Acceso Autorizado");
		break;
		}
		
		notifyAll();
		
	}


}
