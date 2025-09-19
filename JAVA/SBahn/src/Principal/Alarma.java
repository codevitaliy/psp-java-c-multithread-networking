package Principal;


import java.util.Scanner;

public class Alarma extends Thread {

	private Control control;
	private Scanner scanner;
	
	public Alarma(Control control) {
		
		this.control = control;
		this.scanner = new Scanner(System.in);
		
	}
	
	@Override
	public void run() {
		

			String notificacion = scanner.nextLine();
			
			if(notificacion.equalsIgnoreCase("ALARMA")) {
				
				control.activarAlarma();
					
				
			}else {
				System.out.println("no se ha detectado alarma");
			}
		}
	}
	
