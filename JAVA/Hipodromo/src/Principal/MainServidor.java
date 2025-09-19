package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServidor {
	public static void main(String[] args) {
		
		try {
			
			ServerSocket server = new ServerSocket(9000);
			Socket socket;
			boolean fin = false;
			Pista pista = new Pista();
			CaballoRemoto caballoRemoto;
			Juez juez = new Juez(pista);
			juez.start();
			
			while(!fin) {
				
				socket = server.accept();
				caballoRemoto = new CaballoRemoto(socket,pista);
				caballoRemoto.start();
				
			}
			
			server.close();
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
