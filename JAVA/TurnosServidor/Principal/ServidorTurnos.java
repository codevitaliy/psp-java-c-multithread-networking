package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTurnos 
{

	public static void main(String[] args) 
	{
		ServerSocket SocketServidor=null;
		Socket SocketCliente;
		boolean fin = false;
		HiloTurno hiloTurno;
		Central central = new Central(); 
		
		try 
		{
			SocketServidor = new ServerSocket(9000);
			Consola consola = new Consola(central,SocketServidor);
			consola.start();
			System.out.println("Servidor Turnos disponible");
		} 
		catch (IOException e1)	{fin = true;}
		try 
		{
			while (!fin)
			{
				SocketCliente = SocketServidor.accept();
				hiloTurno = new HiloTurno(SocketCliente,central);
				hiloTurno.start();
			}
			SocketServidor.close();
		} catch (IOException e){}
	}
}