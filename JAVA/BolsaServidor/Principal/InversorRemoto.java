package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InversorRemoto extends Thread 
{
	private Parquet parquet;
	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	
	public InversorRemoto (Socket socket, Parquet parquet) throws IOException
	{
		this.parquet = parquet;
		this.socket = socket;
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(flujoEntrada);
		this.salida = new ObjectOutputStream(flujoSalida);
	}
	
	public void run()
	{
		Oferta oferta;
		oferta = recibirOferta();
		switch (oferta.getTipo())
		{
		case COMPRA:
			try 
			{
				parquet.comprar(oferta);
				enviarOferta(oferta.getOperacion());
			} catch (InterruptedException e) {e.printStackTrace();}
			break;
		case VENTA:
			try 
			{
				parquet.vender(oferta);
				enviarOferta(oferta.getOperacion());				
			} catch (InterruptedException e) {e.printStackTrace();}
			break;
		}
		close();
	}
	
	public void close() 
	{
		try 
		{
			this.salida.close();
			this.entrada.close();
			this.flujoSalida.close();
			this.flujoEntrada.close();
			this.socket.close();
		} catch (IOException e) {e.printStackTrace();} 
	}
	
	public void enviarOferta(Oferta oferta)
	{
		try 
		{
			salida.writeObject(oferta);
			salida.flush();
		} catch (IOException e) {e.printStackTrace();}
	}

	public Oferta recibirOferta()
	{
		Oferta oferta=null;
		try 
		{
			oferta = (Oferta) entrada.readObject();
		} 
		catch (IOException e) {e.printStackTrace();} 
		catch (ClassNotFoundException e) {e.printStackTrace();}
		return oferta;
	}
}
