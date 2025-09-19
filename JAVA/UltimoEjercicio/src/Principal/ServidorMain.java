package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMain {
	
	public static void main(String[] args) {
		
		
		try {
			
			
			
			
			
			ServerSocket serverSocket = new ServerSocket(8000);
			Socket socket;
			boolean fin = false;
			Servicio servicio = new Servicio();
			ClienteRemoto clienteRemoto;
			
			
			Thread thread = new Thread(() -> {
				
				boolean finDos = false;
				
				while(!finDos) {
					
					System.out.println("nuevo thread");
					
				}
				
			});
			
			thread.start();
			
			
			
			
			while(!fin) {
				
				socket = serverSocket.accept();
				clienteRemoto = new ClienteRemoto(socket, servicio);
				clienteRemoto.start();
				
			}
			
			serverSocket.close();
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
