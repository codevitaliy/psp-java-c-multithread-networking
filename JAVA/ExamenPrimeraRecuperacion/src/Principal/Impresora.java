package Principal;

public class Impresora {
	
	private Tipo tipo;
	private boolean ocupada;
	
	public Impresora() {
		
		int numRandom = (int)(Math.random()*10+1);
		
		if(numRandom > 5) {
			
			tipo = Tipo.B_N;
			
		}else {
			tipo = Tipo.COLOR;
		}
		
		this.ocupada = false;
		
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public Boolean getOcupada() {
		return ocupada;
	}
	
	public void setOcupada(boolean estado) {
		
		this.ocupada = estado;
		
	}

}
