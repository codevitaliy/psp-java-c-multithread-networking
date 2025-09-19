package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServidor 
{
	public static void main(String[] args) throws IOException 
	{
		ServerSocket serverSocket = new ServerSocket(9000);
		Socket socketCliente;
		Cliente cliente;
		Control control;
		boolean fin = false;
		
		control = new Control();
		while (!fin)
		{
			socketCliente = serverSocket.accept();
			cliente = new Cliente (socketCliente,control);
			cliente.start();
		}
		serverSocket.close();
	}
}
