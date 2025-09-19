package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMain {
	public static void main(String[] args) {
		
		try {
			
			ServerSocket server = new ServerSocket(8000);
			Socket socket;
			boolean fin = false;
			Servicio servicio = new Servicio();
			ClienteRemoto clienteRemoto;
			
			ConsolaOperador consolaOperador = new ConsolaOperador(servicio);
			consolaOperador.start();
			
			
			while(!fin) {
				
				socket = server.accept();
				
				clienteRemoto = new ClienteRemoto(socket,servicio);
				
				clienteRemoto.start();
				
			}
			
			server.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
}
