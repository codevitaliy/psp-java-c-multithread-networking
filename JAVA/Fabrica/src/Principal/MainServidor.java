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
			Control control = new Control();
			FabricaRemoto fabricaRemoto;
			
			while(!fin) {
				
				socket = server.accept();
				fabricaRemoto = new FabricaRemoto(socket,control);
				fabricaRemoto.start();
	
			}
			
			server.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
