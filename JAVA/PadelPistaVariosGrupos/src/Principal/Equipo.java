package Principal;

public class Equipo extends Thread {
	
	private PistaPadel pista;
	private int idEquipo;
	private int numJugadores;

	
	
	public Equipo(PistaPadel pista, int idEquipo) {
		
		this.pista = pista;
		this.idEquipo = idEquipo;
		numJugadores = (int) (Math.random() * 4 + 1);
	}
	
	
	@Override
	public void run() {
		
		try {
			pista.entrarPista(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public int getNumJugadores() {
		return numJugadores;
	}

	
	public int getIdEquipo() {
		return idEquipo;
	}


	

	
	
	
	
	
	

}
