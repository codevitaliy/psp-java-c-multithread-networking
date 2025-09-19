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
			Control control = new Control();
			RemoteConvoy remoteConvoy;
			Alarma alarma = new Alarma(control);
			alarma.start();
	
			while(!fin) {
				
				socket = server.accept();
				remoteConvoy = new RemoteConvoy(socket, control);
				remoteConvoy.start();
				
			}
			
			server.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}

}
