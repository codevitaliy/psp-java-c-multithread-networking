package Principal;

public class MainBanco {

	public static void main(String[] args) {
		
		
		Cuenta cuenta = new Cuenta();
		
		GestorBanco gestorBanco = new GestorBanco(cuenta);
		
		Transferencia[] transfIngreso = new Transferencia[120];
		Transferencia[] transGasto = new Transferencia[120];
		
		for(int i = 0; i < transfIngreso.length ; i++) {
			
			if(i < 40) {
				transfIngreso[i] = new Transferencia(gestorBanco, Tipo.INGRESAR, 100);
			}else if (i <60) {
				transfIngreso[i] = new Transferencia(gestorBanco, Tipo.INGRESAR, 50);
			}else {
				transfIngreso[i] = new Transferencia(gestorBanco,Tipo.INGRESAR,20);
			}
		}
		
		
		for (int i = 0; i < transGasto.length; i++) {
		    if (i < 40) {
		        transGasto[i] = new Transferencia(gestorBanco, Tipo.RETIRAR, 100);
		    } else if (i < 60) {
		        transGasto[i] = new Transferencia(gestorBanco, Tipo.RETIRAR, 50);
		    } else {
		        transGasto[i] = new Transferencia(gestorBanco, Tipo.RETIRAR, 20);
		    }
		}


		for (int i = 0; i < transfIngreso.length; i++) {
		    transfIngreso[i].start();
		    transGasto[i].start();
		}


		for (int i = 0; i < transfIngreso.length; i++) {
		    try {
		        transfIngreso[i].join();
		        transGasto[i].join();
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}
		
		

		System.out.println("Todas las transferencias han terminado");
		System.out.println("Total: " + cuenta.getSaldo());
		
	
		
		
		
	

 }
}