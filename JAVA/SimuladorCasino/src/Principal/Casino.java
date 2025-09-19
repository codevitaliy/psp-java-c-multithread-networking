package Principal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Casino {

	private boolean croupierOcupado;
	private double dineroBanco;
	private long duracionPartida;
	private long inicioMilisegundos;
	private boolean rondaEmpezada;
	private int contadorJugadores;
	private boolean casinoCerrado;
	private Map<Jugador, ArrayList<Integer>> mapApuestas = new LinkedHashMap<>();
	private Map<Jugador, ParImpar> mapApuestasParImpar = new LinkedHashMap<>();

	public Casino() {
		this.croupierOcupado = false;
		this.dineroBanco = 50000;
		this.duracionPartida = 10000;
		this.inicioMilisegundos = 0;
		this.contadorJugadores = 0;
		this.casinoCerrado = false;
	}

	public synchronized void empiezaPartida() {
		System.out.println("=== INICIO DE PARTIDA EN EL CASINO ===");
		this.inicioMilisegundos = System.currentTimeMillis();
	}

	public synchronized void jugarAlNumero(Jugador jugador) throws InterruptedException {
		contadorJugadores++;
		System.out.println("Jugador " + jugador.getIdJugador() + " entra a jugar AL NÚMERO");

		if (jugador.getDineroJugador() < 0) {
			System.out.println("Jugador " + jugador.getIdJugador() + " en bancarrota. No puede jugar.");
			return;
		}

		while (croupierOcupado) {
			System.out.println("Croupier ocupado. Jugador " + jugador.getIdJugador() + " espera...");
			wait();
		}

		croupierOcupado = true;

		jugador.elegirApuestas();
		jugador.pagarApuesta(10);
		mapApuestas.put(jugador, jugador.cogerApuestas());

		System.out.println("Jugador " + jugador.getIdJugador() + " ha apostado al número: " + jugador.imprimirApuestas());

		croupierOcupado = false;
		notifyAll();
	}

	public synchronized void jugarParImpar(Jugador jugador) throws InterruptedException {
		contadorJugadores++;
		System.out.println("Jugador " + jugador.getIdJugador() + " entra a jugar PAR/IMPAR");

		if (jugador.getDineroJugador() < 0) {
			System.out.println("Jugador " + jugador.getIdJugador() + " en bancarrota. No puede jugar.");
			return;
		}

		while (croupierOcupado) {
			System.out.println("Croupier ocupado. Jugador " + jugador.getIdJugador() + " espera...");
			wait();
		}

		croupierOcupado = true;

		jugador.elegirApuestas();
		jugador.pagarApuesta(10);
		mapApuestasParImpar.put(jugador, jugador.getParImpar());

		System.out.println("Jugador " + jugador.getIdJugador() + " ha apostado a: " + jugador.getParImpar());

		croupierOcupado = false;
		notifyAll();
	}

	public synchronized void jugarMartingala(Jugador jugador) throws InterruptedException {
    contadorJugadores++;
    System.out.println("Jugador " + jugador.getIdJugador() + " entra a jugar con MARTINGALA");

    if (jugador.getDineroJugador() < 0) {
        System.out.println("Jugador " + jugador.getIdJugador() + " en bancarrota. No puede jugar.");
        return;
    }

    while (croupierOcupado) {
        System.out.println("Croupier ocupado. Jugador " + jugador.getIdJugador() + " espera...");
        wait();
    }

    croupierOcupado = true;

    jugador.elegirApuestas();

    int apuestaActual = jugador.getApuestaMartingala();
    jugador.pagarApuesta(apuestaActual);  // paga la apuesta actual (puede empezar en 10)
    mapApuestas.put(jugador, jugador.cogerApuestas());

    System.out.println("Jugador " + jugador.getIdJugador() + " ha apostado con martingala " + apuestaActual + " a: " + jugador.imprimirApuestas());

    croupierOcupado = false;
    notifyAll();
}


	public synchronized void resultadoRuleta() throws InterruptedException {
		while (contadorJugadores < 14) {
			wait();
		}

		int numeroGanador = (int) (Math.random() * 37);
		ParImpar parImpar = (Math.random() > 0.5) ? ParImpar.IMPAR : ParImpar.PAR;

		System.out.println("\n=== RESULTADO DE LA RULETA ===");
		System.out.println("Número ganador: " + numeroGanador);
		System.out.println("Resultado PAR/IMPAR: " + parImpar);

		for (Map.Entry<Jugador, ArrayList<Integer>> entrada : mapApuestas.entrySet()) {
			Jugador jugador = entrada.getKey();
			ArrayList<Integer> apuestas = entrada.getValue();

			for (Integer apuesta : apuestas) {
				if (apuesta == numeroGanador) {
					jugador.agregarDinero(10 * 36);
					System.out.println("Jugador " + jugador.getIdJugador() + " acertó con el número " + apuesta + ". ¡Gana 360!");
				}
			}
		}

		for (Map.Entry<Jugador, ParImpar> entrada : mapApuestasParImpar.entrySet()) {
			Jugador jugador = entrada.getKey();
			ParImpar apuesta = entrada.getValue();

			if (apuesta == parImpar) {
				jugador.agregarDinero(20);
				System.out.println("Jugador " + jugador.getIdJugador() + " acertó PAR/IMPAR. ¡Gana 20!");
			}
		}

		System.out.println("=== FIN DE LA RONDA ===\n");
		
		resetRonda();
		
		long contadorMilisegundos = System.currentTimeMillis();
		
		if(contadorMilisegundos - inicioMilisegundos > this.duracionPartida) {
			
			System.out.println("EL CASINO CIERRA");
			
			casinoCerrado = true;
			
			return;
			
		}
		
	

	}
	
	
	public void resetRonda() {
		
		contadorJugadores = 0;
		mapApuestas.clear();
		mapApuestasParImpar.clear();
		
		
	}
	
	public boolean isCasinoCerrado() {
		return this.casinoCerrado;
		
	}
	
	
	
	
	
	
}
