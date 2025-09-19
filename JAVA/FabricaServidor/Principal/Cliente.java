package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente extends Thread
{
	private Socket socket;
	private Control control;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private TIPO tipo;
	private boolean fin;
	
	public Cliente (Socket socket,Control control) throws IOException
	{
		this.socket = socket;
		this.control = control;
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(flujoEntrada);
		this.salida = new ObjectOutputStream(flujoSalida);
		this.fin = false;
	}
	
	public void run()
	{
		Consigna consigna;

		consigna = recibirMensaje();
		tipo = consigna.getTipo();
		int id = 0;
		switch (tipo)
		{
			case CONTENEDOR:
				System.out.println("Contenedor conectado..."+consigna);
				break;
			case ROBOT:
				System.out.println("Robot conectado..."+consigna);
				try 
				{
					control.esperarContenedor();
				} catch (InterruptedException e1) 
				{
					e1.printStackTrace();
				}
				break;
			default:
				System.out.println("Error: Recibida consigna con tipo desconocido");
				return;
		}
		while (!fin)
		{
			consigna = recibirMensaje();
			//System.out.println("Recibido "+consigna);
			try 
			{
				switch (tipo)
				{
				case CONTENEDOR:
					System.out.println("Establece Capacidad en:"+consigna);
					control.setCapacidad(consigna);
					System.out.println("Aguardo llenado");
					control.aguardarLlenado();
					System.out.println("Cambio Cinta");
					consigna = new Consigna(TIPO.CONTENEDOR,"","AVANZAR CINTA");
					System.out.println("Servidor Envía "+consigna);
					enviarMensaje(consigna); // Esperamos mensaje cinta lista
					consigna = recibirMensaje();
					System.out.println("Recibimos "+consigna);
					//control.reanudar();
					break;
				case ROBOT:
					control.producir(consigna);
					consigna = new Consigna(TIPO.ROBOT,"","START");
					enviarMensaje(consigna);
					break;
				}
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		try 
		{
			this.salida.close();
			this.entrada.close();
			this.flujoSalida.close();
			this.flujoEntrada.close();
			this.socket.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Consigna recibirMensaje()
	{
		Consigna consigna = null;
		try 
		{
			consigna= (Consigna) this.entrada.readObject();
		} catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}
		return consigna;
	}
	
	public void enviarMensaje(Consigna consigna)
	{
		try 
		{
			this.salida.writeObject(consigna);
			this.salida.flush();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
