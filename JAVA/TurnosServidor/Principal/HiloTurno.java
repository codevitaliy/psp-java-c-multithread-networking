package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class HiloTurno extends Thread 
{
	private Socket socket;
	private Central central;
	private InputStream FlujoDeEntrada;
	private ObjectInputStream ObjetosEntrada;
	private Turno turno;
	
	public HiloTurno(Socket socket,Central central) throws IOException
	{
		this.socket = socket;
		this.central = central;
		FlujoDeEntrada = socket.getInputStream();
		ObjetosEntrada = new ObjectInputStream(FlujoDeEntrada);
	}
	
	public void run()
	{
		try 
		{
			central.add(this);
			turno = (Turno)ObjetosEntrada.readObject();
			switch (turno.getTipo())
			{
			case 0: 
				central.serAtendido(turno);
				break;
			case 1:
				central.atender(turno);
				break;
			case 2: // Borrar la lista de clientes
			}
			
		} catch (ClassNotFoundException | IOException | InterruptedException e) {}
		
		try 
		{
			ObjetosEntrada.close();
			FlujoDeEntrada.close();
			central.remove(this);
		} catch (IOException e) {}
	}
}
