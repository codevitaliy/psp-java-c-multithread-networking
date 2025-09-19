package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends Thread 
{
		private Socket socket;
		private InputStream FlujoDeEntrada;
		private OutputStream FlujoDeSalida;
		private ObjectInputStream ObjetosEntrada;
		private ObjectOutputStream ObjetosSalida;
		private Trabajo trabajo;
		private Scanner sc; 
		private int tipo;
		private String nombre;
		private String descripcion;
		private String solucion;
		
		public Cliente ()
		{
			sc = new Scanner(System.in);
			System.out.print("Nick:");
			nombre=sc.nextLine();
			System.out.print("Cliente(0) o Técnico(1): ");
			tipo = sc.nextInt();
			descripcion=sc.nextLine();
			if (tipo == 0)
			{
				System.out.print("Describa el problema: ");
				descripcion = sc.nextLine();
			}
			trabajo = new Trabajo(nombre,tipo,descripcion);	
		}
		public void run()
		{
			try 
			{
				socket = new Socket("localhost",8000);
				FlujoDeEntrada = socket.getInputStream();
				FlujoDeSalida = socket.getOutputStream();
				ObjetosSalida = new ObjectOutputStream(FlujoDeSalida);
				ObjetosEntrada = new ObjectInputStream(FlujoDeEntrada);

				ObjetosSalida.writeObject(trabajo); // Enviamos el trabajo 
				ObjetosSalida.flush();
				
				trabajo = null;
				do 
				{
					trabajo = (Trabajo) ObjetosEntrada.readObject();
				} while (trabajo==null);
				
				System.out.println (trabajo);
				if (tipo==1) // Si es un técnico lo que ha recibido debe resolverlo
				{
					System.out.println("Indique la solución propuesta: ");
					solucion=sc.nextLine();
					trabajo.setSolucion(solucion);
					ObjetosSalida.writeObject(trabajo);
					ObjetosSalida.flush();
				}
				
				ObjetosSalida.close();
				ObjetosEntrada.close();
				FlujoDeSalida.close();
				FlujoDeEntrada.close();
				socket.close();
				sc.close();
			} catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
		}
}
