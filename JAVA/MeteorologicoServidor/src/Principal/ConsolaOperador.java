package Principal;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ConsolaOperador extends Thread {
	
	private Scanner scanner;
	private Servicio servicio;
	private Peticion peticion;
	private ObjectOutputStream salida;
	
	public ConsolaOperador(Servicio servicio) throws FileNotFoundException, IOException {
		
		this.servicio = servicio;
		this.salida = new ObjectOutputStream(new FileOutputStream("./fichero.log"));
		
	}
	
	@Override
	public void run() {
		
		boolean fin = false;
		
		String id = "";
		String respuesta = "MENSAJE DE PRUEBA";
	
		scanner = new Scanner(System.in);
		
		do {
			
			try {
				
				System.out.println("LIST O ENTER");
				
				String leerEntrada;
				
				leerEntrada = scanner.nextLine();
				
				switch(leerEntrada) {
				
				case "LIST":
					
					String peticiones = servicio.listadoPeticionesPtes();
					System.out.println(peticiones);
					
				break;
				
				case "ENTER":
			    id = scanner.nextLine();
			    peticion = servicio.atenderPeticion(id, respuesta);
			    servicio.atenderPeticionLocalida(peticion, respuesta);
			    if (peticion != null) {
			        salida.writeObject(peticion);
			    }
			    break;
				case "FINAL":
					fin = true;
					break;
			
				}
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}while(!fin);
		
		try {
			salida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	

	
	

	
	
	
}
