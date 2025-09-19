package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMain {
	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(9000);
			Socket socket;
			boolean fin = false;
			ControlTrafico control = new ControlTrafico();
			VehiculoDos vehiculoDos;
			
			while(!fin) {
				
				socket = server.accept();
				vehiculoDos = new VehiculoDos(socket,control);
				vehiculoDos.start();
				
				
			}
			
			server.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
