package Principal;

import java.util.ArrayList;

enum TipoJugador {
	ALNUMERO, PARIMPAR, MARTINGALA
}

enum ParImpar {
	PAR, IMPAR;
}

public class Jugador extends Thread {

	private Casino casino;
	private int idJugador;
	private ArrayList<Integer> apuestas;
	private double dineroJugador;
	private TipoJugador tipoJugador;
	private ParImpar parImpar;
	private int apuestaMartingala = 10; // apuesta inicial para Martingala
	private Integer numeroMartingalaFijo = null;

	public Jugador(Casino casino, int idJugador, TipoJugador tipoJugador) {

		this.casino = casino;
		this.tipoJugador = tipoJugador;
		this.idJugador = idJugador;
		this.apuestas = new ArrayList<>();
		this.dineroJugador = 1000.00;
		this.parImpar = null;

	}

	@Override
	public void run() {
		try {
			while (!casino.isCasinoCerrado()) {
				switch (tipoJugador) {
				case ALNUMERO:
					casino.jugarAlNumero(this);
					break;
				case PARIMPAR:
					casino.jugarParImpar(this);
					break;
				case MARTINGALA:
					casino.jugarMartingala(this);
					break;
				}
				// Espera un poco para no saturar el casino
				Thread.sleep(500);
			}
			System.out.println("Jugador " + idJugador + " termina porque el casino cerró.");
		} catch (InterruptedException e) {
			System.out.println("Jugador " + idJugador + " interrumpido.");
		}
	}

	public void elegirApuestas() {
		
		resetearApuestas();

		if (tipoJugador == tipoJugador.ALNUMERO) {

			int numeroApuestas = (int) (Math.random() * 3 + 1);

			for (int i = 0; i < numeroApuestas; i++) {

				Integer numeroRandom = (int) (Math.random() * 36 + 1);

				apuestas.add(numeroRandom);

			}

		} else if (tipoJugador == TipoJugador.PARIMPAR) {

			int numeroRandom = (int) (Math.random() * 1 + 1);

			if (numeroRandom > 1) {

				this.parImpar = ParImpar.PAR;

			} else {
				this.parImpar = ParImpar.IMPAR;
			}

		} else if (tipoJugador == TipoJugador.MARTINGALA) {
			if (numeroMartingalaFijo == null) {
				numeroMartingalaFijo = (int) (Math.random() * 36 + 1);
			}
			apuestas.clear(); // limpiar apuestas previas
			apuestas.add(numeroMartingalaFijo);

		}
	}

	public void pagarApuesta(int n) {
		this.dineroJugador = this.dineroJugador - n;
	}

	public double getDineroJugador() {
		return dineroJugador;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public ArrayList<Integer> cogerApuestas() {
		return this.apuestas;
	}

	public String imprimirApuestas() {
		String apuesta = "";
		for (Integer numero : apuestas) {
			apuesta = apuesta + numero + " ";
		}
		return apuesta.trim();
	}

	public ParImpar getParImpar() {
		return this.parImpar;
	}

	public void agregarDinero(int n) {
		this.dineroJugador += n;
	}

	public int getApuestaMartingala() {
		return apuestaMartingala;
	}

	public void doblarApuestaMartingala() {
		apuestaMartingala *= 2;
	}

	public void resetApuestaMartingala() {
		apuestaMartingala = 10;
	}

	public void resetearApuestas() {
		this.parImpar = null;
		apuestas.clear();
	}

}
