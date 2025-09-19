package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServidorCruce
{
	public static void main(String[] args) 
	{
		try 
		{
			ServerSocket servidor = new ServerSocket(9000);
			Socket socket;
			boolean fin = false;
			Cruce cruce = new Cruce();
			Vehiculo vehiculo;
			
			while(!fin) 
			{
				
				socket = servidor.accept();
				vehiculo = new Vehiculo(socket,cruce);
				vehiculo.start();
			}
			servidor.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}