package Principal;

public class GestorPadel {

	private Pista[] pistas;
	private final int NUM_MAX_PISTAS = 4;
	private int cola; // grupos esperando para entrar

	public GestorPadel() {
		cola = 0;
		pistas = new Pista[NUM_MAX_PISTAS];
		for (int i = 0; i < NUM_MAX_PISTAS; i++) {
			pistas[i] = new Pista();
		}
		System.out.println("[GestorPadel] Inicializadas " + NUM_MAX_PISTAS + " pistas.");
	}

	public synchronized void entrarPista(Grupo grupo) throws InterruptedException {
		cola++;
		System.out.println("[ENTRADA] Grupo " + grupo.getIdGrupo() + " con " + grupo.getNumJugadores() + " jugadores entra a la cola. Cola total: " + cola);

		while (todasLasPistasOcupadas() || !hayGrupoDisponible(grupo)) {
			if (todasLasPistasOcupadas()) {
				System.out.println("[ESPERA] Todas las pistas ocupadas. Grupo " + grupo.getIdGrupo() + " esperando.");
			} else {
				System.out.println("[ESPERA] No hay hueco suficiente para grupo " + grupo.getIdGrupo() + ". Esperando.");
			}
			wait();
		}

		for (Pista pista : pistas) {
			int libres = pista.getCapacidad() - pista.getNumJugadores();
			System.out.println("[COMPROBACIÓN] Pista " + pista.getIdPista() + " tiene " + libres + " plazas libres. Grupo " + grupo.getIdGrupo());

			if (grupo.getNumJugadores() <= libres) {
				pista.addJugadores(grupo.getNumJugadores());
				cola--;
				System.out.println("[ENTRADA EXITOSA] Grupo " + grupo.getIdGrupo() + " asignado a pista " + pista.getIdPista() + ". Quedan " + cola + " en cola.");
				break;
			}
		}
	}

	public synchronized void jugarPartido() throws InterruptedException {
		System.out.println("[JUEGO] Se termina el partido en pistas ocupadas.");
		Thread.sleep(1000);

	}

	public synchronized void salirReset() {
		System.out.println("[RESET] Vaciar y desbloquear todas las pistas.");
		for (Pista pista : pistas) {
			pista.vaciarPista();
			pista.desbloquearPista();
		}
		notifyAll();
	}

	public boolean todasLasPistasOcupadas() {
		int ocupadas = 0;
		for (Pista pista : pistas) {
			if (pista.isPistaOcupada()) ocupadas++;
		}
		boolean todas = ocupadas == NUM_MAX_PISTAS;
		if (todas) System.out.println("[INFO] Todas las pistas están ocupadas.");
		return todas;
	}

	public boolean hayGrupoDisponible(Grupo grupo) {
		for (Pista pista : pistas) {
			int libres = pista.getCapacidad() - pista.getNumJugadores();
			if (grupo.getNumJugadores() <= libres) {
				System.out.println("[INFO] Hay hueco para grupo " + grupo.getIdGrupo() + " en pista " + pista.getIdPista());
				return true;
			}
		}
		System.out.println("[INFO] No hay hueco disponible para grupo " + grupo.getIdGrupo());
		return false;
	}

	public boolean hayCuantroEnPista() {
		for (Pista pista : pistas) {
			if (pista.getNumJugadores() == 4) {
				System.out.println("[INFO] Pista " + pista.getIdPista() + " está completa con 4 jugadores.");
				pista.bloquerPista();
				return true;
			}
		}
		return false;
	}
}
