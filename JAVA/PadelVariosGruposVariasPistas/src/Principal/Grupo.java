package Principal;

public class Grupo extends Thread {
	
	private GestorPadel gestor;
	private int idGrupo;
	private int numJugadores;

	
	
	public Grupo(GestorPadel gestor, int idGrupo) {
		
		this.gestor = gestor;
		this.idGrupo = idGrupo;
		numJugadores = (int) (Math.random() * 4 + 1);
	}
	
	
	@Override
	public void run() {
	    try {
	        gestor.entrarPista(this);
	        gestor.jugarPartido();
	        gestor.salirReset();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	
	public int getNumJugadores() {
		return numJugadores;
	}

	
	public int getIdGrupo() {
		return idGrupo;
	}
	
	


	

	
	
	
	
	
	

}
