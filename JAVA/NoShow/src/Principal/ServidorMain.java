
package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorMain {
	public static void main(String[] args) {
		
		
		
		try {
			
			ServerSocket server = new ServerSocket(8000);
			Socket socket;
			boolean fin = false;
			Sala sala = new Sala();
			HiloReserva hiloReserva;
			
			Thread anfitrion = new Thread(() -> {
				
				Scanner sc = new Scanner(System.in);
				 System.out.println("[Anfitrión] Esperando entrada de número de comensales para liberar mesa...");
				
				while(!false) {
					
					int numComensales = Integer.parseInt(sc.nextLine());
					
					sala.putMesa(numComensales);
					System.out.println("[Anfitrión] Mesa para " + numComensales + " comensales añadida.");
					
				}
				
			});
			
			anfitrion.start();
			
			while(!fin) {
				socket = server.accept();
				hiloReserva = new HiloReserva(socket, sala);
				hiloReserva.start();
			}
			
			server.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
