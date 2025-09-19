package Principal;

public class Pista {
	
	private static int contador;
	private int idPista;
	private int nMax;
	private int nJugadores;
	private boolean pistaBloqueada;
	
	public Pista() {
		
		contador++;
		idPista = contador;
		nMax = 4;
		nJugadores = 0;
		pistaBloqueada = false;
		
		
	}
	
	public int getIdPista() {
		return idPista;
	}
	
	public int getCapacidad() {
		return nMax;
	}
	
	public int getNumJugadores() {
		return this.nJugadores;
	}
	
	public void addJugadores(int n) {
		 nJugadores += n;
	}
	
	public void vaciarPista() {
		nJugadores = 0;
	}
	
	public void bloquerPista() {
			pistaBloqueada = true;	
	}
	
	public boolean isPistaOcupada() {
		return pistaBloqueada;
	}
	
	public void desbloquearPista() {
		pistaBloqueada = false;
	}
	
	

}
