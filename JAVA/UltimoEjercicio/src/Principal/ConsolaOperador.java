package Principal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

enum Tipo {
	GRANDE,PEQUE
}

public class ConsolaOperador extends Thread {
	
	private Scanner scanner;
	private Servicio servicio;
	private boolean fin;
	private Peticion peticion;
	private FileOutputStream ficheroSalida;
	private ObjectOutputStream salida;
	private Tipo tipo;
	
	
	
	public ConsolaOperador(Servicio servicio) throws IOException {
		
		this.servicio = servicio;
		this.scanner = new Scanner(System.in);
		this.fin = false;
		this.ficheroSalida = new FileOutputStream("./fichero.log");
		this.salida = new ObjectOutputStream(ficheroSalida);
		
	}
	
	@Override
	public void run() {
		
		String respuesta;
		String[] comando;
		
		if(tipo == Tipo.GRANDE){
			System.out.println("hola");
		
		}
		
		switch(tipo) {
		
		case GRANDE:
			break;
		case PEQUE:
			break; 
		}
		
		
		String nuevaLinea = scanner.nextLine();
		
		int numeroString = Integer.parseInt(nuevaLinea);
		
		String numeroAString = String.valueOf(numeroString);
		
		
		do {
			
			respuesta = scanner.nextLine();
			
			comando = respuesta.split(respuesta);
			
			System.out.println("ENVIAR | LIST");
			
			switch (comando[0]) {
			
			case "ENVIAR" :
				
				try {
					
					peticion = servicio.atenderPeticion(comando[1], comando[2]);
					
					
					
					salida.writeObject(peticion);
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			}
			
		}while(!fin);
		
		try {
			salida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ficheroSalida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		
		
	}
	
	
	public void terminar() {
		this.fin = true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
