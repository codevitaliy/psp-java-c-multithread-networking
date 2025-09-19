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
			Consola consola = new Consola(null);
			Control control = new Control(consola);
			consola.setControl(control);
			VehiculoRemoto vehiculoRemoto;
			consola.start();
			
			
			while(!fin) {
				
				socket = server.accept();
				vehiculoRemoto = new VehiculoRemoto(socket,control);
				vehiculoRemoto.start();
				
			}
			
			server.close();
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
}
