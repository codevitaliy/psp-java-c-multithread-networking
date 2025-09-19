package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Consola extends Thread 
{
	private Scanner sc;
	private String comando;
	private boolean fin;
	private Central central;
	private ServerSocket serverSocket;
	
	public Consola(Central central,ServerSocket serverSocket)
	{
		sc = new Scanner (System.in);
		fin = false;
		this.central = central;
		this.serverSocket = serverSocket;
	}
	
	public void run()
	{
		menu();
		while (!fin)
		{
			System.out.println("Comando:");
			comando = sc.nextLine();
			comando = comando.toUpperCase();
			switch (comando)
			{
			case "CERRAR":
				central.terminar();
				try 
				{
					serverSocket.close();
				} catch (IOException e)	{}
				fin = true;
				break;
			case "HELP":
				menu();
				break;
			}
		}
	}
	
	public void menu()
	{
		System.out.println("Consola. Comandos disponibles:"); 
		System.out.println("CERRAR - Detiene el servidor.");
		System.out.println("HELP - Muestra esta ayuda.");
	}
}
