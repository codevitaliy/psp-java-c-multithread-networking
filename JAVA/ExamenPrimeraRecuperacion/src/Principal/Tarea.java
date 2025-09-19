package Principal;

public class Tarea {
	
	private Tipo tipo;
	private Prioridad prioridad;
	
	public Tarea() {
		
		int numRandom = (int)(Math.random()* 10 + 1);
		
		if(numRandom > 5) {
			
			this.tipo = Tipo.B_N;
			
		}else {
			this.tipo = Tipo.COLOR;
		}
		
		int numRandomDos = (int)(Math.random()*10+1);
		
		if(numRandomDos > 5) {
			this.prioridad = Prioridad.ALTA;
		}else {
			this.prioridad = Prioridad.NORMAL;
			
		}
		
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public Prioridad getPrioridad() {
		return prioridad;
	}
	
	

}
