package Principal;

import java.util.ArrayList;

public class GestorImpresion {

	private int numeroImpresorasBN;
	private int numeroImpresorasColor;
	private ArrayList<Impresora> impresoras;

	public GestorImpresion(int numeroImpresorasBN, int numeroImpresorasColor) {

		this.numeroImpresorasBN = numeroImpresorasBN;
		this.numeroImpresorasColor = numeroImpresorasColor;

	}

	private synchronized Impresora solicitarImpresora(int idHilo, Tarea tarea) throws InterruptedException {
		
		Tipo tipo = tarea.getTipo();
		Impresora impresora;

		while(buscarImpresoraDisponible(tipo) == null) {
			
			wait();
			
		}
		
		 return impresora = buscarImpresoraDisponible(tipo);
		
		
		
	}

	private synchronized Impresora buscarImpresoraDisponible(Tipo tipo) throws InterruptedException {

		Impresora impresora = null;

		while (impresora == null) {

			for (Impresora i : impresoras) {

				if (tipo == i.getTipo()) {

					i.setOcupada(true);

					return i;

				}

			}
			
			wait();

		}

		return null;

	}
	
	private synchronized void liberarImpresora(int idHilo, Impresora impresora) {
		
		impresora.setOcupada(false);
		notifyAll();
		
		
	}
	
	
	
	
	

}
