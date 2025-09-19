package Principal;

import java.util.Scanner;

public class Consola extends Thread {
	
	
	private Control control;
	private String version;
	private Scanner sc;
	
	public Consola(Control control) {
		
		this.control = control;
		this.version = "1";
		this.sc = new Scanner(System.in);
		
	}
	
	@Override
	
	public void run() {
		boolean fin = false;
		
		while(!fin) {
			cambiarVersion();
		}
		
		
	}
	
	public synchronized void cambiarVersion() {
		
		System.out.println("Que version quiere usar: ");
		System.out.println("V1: PULSE 1 ");
		System.out.println("V2: PULSE 2 ");
		System.out.println("V3: PULSE 3 ");
		System.out.println("V4: PULSE 4 ");
		System.out.println("V5: PULSE 5 ");
		version = sc.nextLine();
		
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setControl(Control control) {
		this.control = control;
	}

	
	
	
	
	

}
