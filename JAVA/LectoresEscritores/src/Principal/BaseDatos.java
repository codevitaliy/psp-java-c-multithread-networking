package Principal;

public class BaseDatos {
	
	private boolean baseDatosBloqueada;
	private int numLectores;
	
	public BaseDatos() {
		this.baseDatosBloqueada = false;
		this.numLectores = 0;
	}
	
	//----------------------------------------------------------------------------------------------------------------------
	
	public synchronized void entranLectores() {
		
	}
	
	public void leerDatos() {
		
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------

	public synchronized void escribirDatos() {
		
		
		
		
	}
	
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------
}
