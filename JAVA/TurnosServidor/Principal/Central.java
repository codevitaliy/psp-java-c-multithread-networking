package Principal;

import java.util.ArrayList;

public class Central 
{
	private ArrayList<Turno> peticiones;
	private ArrayList<Turno> funcionarios;
	private ArrayList<HiloTurno> hilos;
	private boolean fin;
	
	public Central()
	{
		peticiones = new ArrayList<Turno>();
		funcionarios = new ArrayList<Turno>();
		hilos = new ArrayList<HiloTurno>();
		fin = false;
	}

	public synchronized void atender(Turno turno) throws InterruptedException {
    Turno turnoPeticion = null;
    System.out.println("Nuevo " + turno);

    while (turnoPeticion == null) {
        for (Turno t : peticiones) {
            if (t.getConcepto().equals(turno.getConcepto())) {
                turnoPeticion = t;
                break;
            }
        }

        if (turnoPeticion == null) {
            funcionarios.add(turno);
            wait();
            if (funcionarios.contains(turno)) {
                funcionarios.remove(turno);
            } else {
                return; // ya fue atendido por otro hilo
            }
        }
    }

    peticiones.remove(turnoPeticion);
    System.out.println(turno + " atiende a " + turnoPeticion);
    notifyAll();
}

	
	// Método de interface que invocan los clientes
	public synchronized void serAtendido(Turno turno) throws InterruptedException {
    Turno turnoAtencion = null;
    System.out.println("Nuevo " + turno);

    while (turnoAtencion == null) {
        for (Turno t : funcionarios) {
            if (t.getConcepto().equals(turno.getConcepto())) {
                turnoAtencion = t;
                break;
            }
        }

        if (turnoAtencion == null) {
            peticiones.add(turno);
            wait();
            if (peticiones.contains(turno)) {
                peticiones.remove(turno);
            } else {
                return; // ya fue atendido por otro hilo
            }
        }
    }

    funcionarios.remove(turnoAtencion);
    System.out.println(turno + " es atendido por " + turnoAtencion);
    notifyAll();
}

	
	public synchronized void add(HiloTurno h)
	{
		hilos.add(h);
	}
	
	public synchronized void remove(HiloTurno h)
	{
		hilos.remove(h);
	}
	
	public synchronized void terminar()
	{
		for (Turno t : peticiones)
			System.out.println("Petición sin atender "+t);
			
		peticiones.clear();
		funcionarios.clear();
		for (HiloTurno h : hilos)
			h.interrupt();
	}
}
