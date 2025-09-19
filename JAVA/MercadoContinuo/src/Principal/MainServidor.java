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
			Parquet parquet = new Parquet();
			InversorRemoto inversor;
			
			while(!fin) {
				
				socket = server.accept();
				inversor = new InversorRemoto(socket, parquet);
				inversor.start();
				
			}
			
			server.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
		
	}

}
