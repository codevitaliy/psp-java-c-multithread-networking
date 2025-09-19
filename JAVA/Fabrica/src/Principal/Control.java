package Principal;

import java.util.ArrayList;

public class Control {
	
	private int capacidad;
	private int capacidadRestante;
	private int contadorLlenado;
	private boolean contenedorDisponible;
	private static final int NUM_MAX_ROBOTS = 4;
	private int contadorRobotsEsperando;
	
	public Control() {
		capacidad = 0;
		capacidadRestante = 0;
		contadorLlenado = 0;
		contenedorDisponible = true;
		contadorRobotsEsperando = 0;
		
	}
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
	
	//mientras no haya contenedor disponible los robots esperan
	public synchronized void esperarContenedor(Consigna consigna) throws InterruptedException {
    while (!contenedorDisponible) {
        wait();
    }
}
	
//-----------------------------------------------------------------------------------------------------------------------------------
	//mientras no sea necesario cambiar el contededor la cinta espera 
  public synchronized void aguardarLlenado() throws InterruptedException {
    while (contenedorDisponible) { 
        wait();
    }
}
	
//-----------------------------------------------------------------------------------------------------------------------------------

	//Si el contenedor tiene capacidad el robot arroja su carga. Si no tiene capacidad el robot debe esperar
  public synchronized void producir(Consigna consigna) throws InterruptedException {
    int cargaRobot = Integer.parseInt(consigna.getMensaje());

    while (!contenedorDisponible  || capacidadRestante < cargaRobot) {
        contadorRobotsEsperando++;

        if (contadorRobotsEsperando == NUM_MAX_ROBOTS) {
            contenedorDisponible = false;
            notifyAll(); // 
        }

        wait();
    }

    capacidadRestante -= cargaRobot;
    System.out.println("[CONTROL] Carga añadida: " + cargaRobot + ". Capacidad restante: " + capacidadRestante);

    notifyAll(); // ✅ CAMBIO: para despertar otros robots que estén esperando
}
	
//-----------------------------------------------------------------------------------------------------------------------------------
	//stablece la capacidad del nuevo contendor y notifica a los robots que el contenedor nuevo está disponible.
  public synchronized void setCapacidad(Consigna consigna) {
    int nuevaCapacidad = Integer.parseInt(consigna.getMensaje());

    this.capacidad = nuevaCapacidad;
    this.capacidadRestante = nuevaCapacidad;
    contenedorDisponible = true;
    contadorRobotsEsperando = 0; 

    System.out.println("[CONTROL] Nuevo contenedor disponible: capacidad = " + nuevaCapacidad);
    notifyAll();
}
	
//-----------------------------------------------------------------------------------------------------------------------------------
	
}