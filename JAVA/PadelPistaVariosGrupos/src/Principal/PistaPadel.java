package Principal;

public class PistaPadel {
	
	private boolean pistaBloqueada;
	private int numJugadores;
	private int jugadoresRestantes;

	public PistaPadel() {
		pistaBloqueada = false;
		numJugadores = 0;
		jugadoresRestantes = 4;
	}
	
	public synchronized void entrarPista(Equipo equipo) throws InterruptedException {
		
		
		System.out.println("Cola, equipo: " + equipo.getIdEquipo() + " Jugadores: " + equipo.getNumJugadores());
		
		while(pistaBloqueada || jugadoresRestantes < equipo.getNumJugadores()) {
			
			if(jugadoresRestantes < equipo.getNumJugadores()) {
				System.out.println("Esperando no encajan los jugadores ... id: " + equipo.getIdEquipo() + " Jugadores: " + equipo.getNumJugadores() );
			}else {
				System.out.println("Esperando... id: " + equipo.getIdEquipo() + " Jugadores: " + equipo.getNumJugadores() );
			}
			

			
			wait();
		}
		
		
		numJugadores += equipo.getNumJugadores();
		jugadoresRestantes -= equipo.getNumJugadores();
		
		if(jugadoresRestantes == 0) {
			
			pistaBloqueada = true;
			
			System.out.println("Tenemos suficientes jugadores para jugar: " + numJugadores);
			System.out.println("JUGANDO PARTIDO!");
			
			Thread.sleep(3000);
			
			salirReset();
			
		}
		
		while (jugadoresRestantes > 0) {
	    if (numJugadores > 0) {
	        System.out.println("faltan aun jugadores: " + jugadoresRestantes);
	    }
	    wait();
	}

			
	}
	
	
	public synchronized void salirReset() {
		
		System.out.println("RESETEAMOS LA PISTA!");
		
		
		this.numJugadores = 0;
		this.jugadoresRestantes = 4;
		
		System.out.println("Jugadores: " + numJugadores + " Restantes: " + jugadoresRestantes);
		pistaBloqueada = false;
		notifyAll();
		
	}
	
}
