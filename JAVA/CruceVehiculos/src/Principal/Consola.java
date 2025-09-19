package Principal;

import java.util.Scanner;

public class Consola extends Thread{
	
	private Scanner scanner;
	private Cruce cruce;
	private boolean fin;
	
	
	public Consola(Cruce cruce) {
		
		scanner = new Scanner(System.in);
		this.cruce = cruce;
		this.fin = false;
	}
	
	@Override
	public void run() {
		
		String entrada;
		
		String[] comando;
		
		entrada = scanner.nextLine();
		
		comando = entrada.split("");
		
		do {
			
			switch (comando[0]) {
			case "HACER ESTO" :
				break;
				
			case "FIN":
				terminar();
				break;
			
			
			}
			
			
		}while(!fin);
		
		
		
	}
	
	public void terminar() {
		this.fin = true;
	}
	

}
