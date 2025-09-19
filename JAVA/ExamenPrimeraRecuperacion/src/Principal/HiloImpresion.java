package Principal;

public class HiloImpresion extends Thread {

	private GestorImpresion gestorImpresion;
	private Impresora impresora;
	private Tarea tarea;
	private int idHilo;
	
	public HiloImpresion(GestorImpresion gestorImpresion, int idHilo) {
		
		this.gestorImpresion = gestorImpresion;
		
		this.idHilo = idHilo;
		
	}
	
	
}
