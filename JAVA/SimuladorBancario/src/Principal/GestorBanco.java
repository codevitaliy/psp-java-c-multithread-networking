package Principal;

public class GestorBanco {
	
	private Cuenta cuenta;
	private boolean cuentaEnUso;
	
	public GestorBanco(Cuenta cuenta) {
		this.cuenta = cuenta;
		this.cuentaEnUso = false;
	}
	
	
	
public synchronized void ingresar(Transferencia transferencia) throws InterruptedException {
    
    while (cuentaEnUso) {
        System.out.println("Ingreso esperando: cuenta en uso, esperando turno...");
        wait();
    }
    
    cuentaEnUso = true;
    System.out.println("Ingreso empezando: " + transferencia.getCantidad());
    
    cuenta.aumenterSaldo(transferencia.getCantidad());
    
    System.out.println("Ingreso completado: saldo actual = " + cuenta.getSaldo());
    
    cuentaEnUso = false;
    
    notifyAll();
    System.out.println("Ingreso: notificando a otros hilos");
}

public synchronized void retirar(Transferencia transferencia) throws InterruptedException {
    
    while (cuenta.getSaldo() < transferencia.getCantidad() || cuentaEnUso) {
        System.out.println("Retiro esperando: saldo insuficiente o cuenta en uso, esperando...");
        wait();
    }
    
    cuentaEnUso = true;
    System.out.println("Retiro empezando: " + transferencia.getCantidad());
    
    cuenta.disminuirSaldo(transferencia.getCantidad());
    
    System.out.println("Retiro completado: saldo actual = " + cuenta.getSaldo());
    
    cuentaEnUso = false;
    
    notifyAll();
    System.out.println("Retiro: notificando a otros hilos");
}

	
	

	
	
	

}
