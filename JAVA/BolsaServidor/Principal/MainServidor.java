package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServidor 
{
	public static void main(String[] args) throws IOException 
	{
		ServerSocket socketServer = new ServerSocket(9000);
		Socket socket;
		InversorRemoto inversorRemoto;
		Parquet parquet = new Parquet();
		boolean fin = false;
		
		while (!fin)
		{
			socket = socketServer.accept();
			inversorRemoto = new InversorRemoto(socket,parquet);
			inversorRemoto.start();
		}
		socketServer.close();
	}
}
