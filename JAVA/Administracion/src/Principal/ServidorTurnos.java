package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorTurnos {
	
	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(9000);
			Socket socket;
			boolean fin = false;
			Central central = new Central();
			HiloTurno hilo;
			
			
			Thread hiloCerrar = new Thread(() -> {
				
				Scanner sc = new Scanner(System.in);
				
				while(!fin) {
					
					String leerEntrada = sc.nextLine();
					
					if(leerEntrada.equals("CERRAR")) {
						
						
						System.out.println("Se cierra el chiringo");
						
						try {
							server.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			});
			
			
			while(!fin) {
				
				socket = server.accept();
				hilo = new HiloTurno(socket, central);
				hilo.start();
				
			}
			
			server.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	


}
