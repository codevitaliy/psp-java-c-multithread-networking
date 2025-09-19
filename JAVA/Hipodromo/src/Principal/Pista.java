package Principal;

import java.util.ArrayList;

public class Pista {

	private static final int NUM_CABALLOS = 16;
	private ArrayList<Integer> caballosAutorizados;
	private ArrayList<Mensaje> caballosPreparados;
	private ArrayList<Mensaje> caballosFinalizados;
	private boolean carreraIniciada;
	private boolean hayGanador;
	private String textoGanador;

	public Pista() {
		this.caballosAutorizados = new ArrayList<>();
		this.caballosPreparados = new ArrayList<>();
		this.caballosFinalizados = new ArrayList<>();
		this.carreraIniciada = false;
		this.hayGanador = false;
		this.textoGanador = "";
	}

	public synchronized void setCaballosAutorizados(ArrayList<Integer> listaCaballos) {
		caballosAutorizados.clear();
		caballosAutorizados.addAll(listaCaballos);
		System.out.println("Caballos autorizados: " + caballosAutorizados);
		notifyAll();
	}

	public synchronized void iniciarCarrera() {
		carreraIniciada = true;
		System.out.println("¡Juez ha dado la salida!");
		notifyAll();
	}

	public synchronized void esperarSalida(Mensaje mensaje) throws InterruptedException {
		int id = mensaje.getNumero();

		// Espera hasta que se configure la lista
		while (caballosAutorizados.isEmpty()) {
			wait();
		}

		boolean autorizado = caballosAutorizados.contains(id);

		if (!autorizado) {
			System.out.println("Caballo " + id + " NO autorizado. Espera a que haya ganador.");
			while (!hayGanador) {
				wait();
			}
			System.out.println("Caballo " + id + " desbloqueado tras ganador.");
			return;
		}

		System.out.println("Caballo AUTORIZADO " + id + " preparado.");
		caballosPreparados.add(mensaje);

		// Avisar si todos los autorizados están preparados
		if (caballosPreparados.size() == caballosAutorizados.size()) {
			notifyAll();
		}

		// Esperar a que todos estén listos Y el juez haya dado la salida
		while (!carreraIniciada || caballosPreparados.size() < caballosAutorizados.size()) {
			wait();
		}

		System.out.println("Caballo " + id + " recibe GO!");
	}

	public synchronized String terminarCarrera(Mensaje mensaje) {
		int id = mensaje.getNumero();

		if (!caballosFinalizados.contains(mensaje)) {
			caballosFinalizados.add(mensaje);
			System.out.println("Caballo " + id + " ha TERMINADO.");
		}

		if (!hayGanador && caballosAutorizados.contains(id)) {
			hayGanador = true;
			textoGanador = id + "-GANADOR";
			System.out.println("¡GANADOR: " + textoGanador);
			notifyAll(); // libera a los no autorizados
		}

		if (caballosFinalizados.size() == NUM_CABALLOS) {
			System.out.println("Todos han terminado. Reiniciando.");
			reset();
		}

		return textoGanador;
	}

	private void reset() {
		caballosAutorizados.clear();
		caballosPreparados.clear();
		caballosFinalizados.clear();
		carreraIniciada = false;
		hayGanador = false;
		textoGanador = "";
	}
}
