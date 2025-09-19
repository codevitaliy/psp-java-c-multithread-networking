package Principal;

import java.io.IOException;
import java.net.UnknownHostException;

public class MainClient 
{
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		Cliente cliente = new Cliente();
		cliente.start();
	}

}
