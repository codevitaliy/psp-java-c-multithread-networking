package Principal;

public class Cuenta {
	
	private int saldo;
	
	public Cuenta() {
		this.saldo =100;
	}
	
	public int getSaldo() {
		return saldo;
	}
	
	public void aumenterSaldo(int cantidadDinero) {
		this.saldo += cantidadDinero;
	}
	
	public void disminuirSaldo(int cantidadDinero) {
		this.saldo -= cantidadDinero;
	}

}
