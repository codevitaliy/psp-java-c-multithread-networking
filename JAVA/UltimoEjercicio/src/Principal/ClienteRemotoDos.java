package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClienteRemotoDos extends Thread {
	
	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Peticion peticion;
	private Servicio servicio;
	
	public ClienteRemotoDos(Socket socket,Servicio servicio) throws IOException {
		
		this.socket = socket;
		this.servicio = servicio;
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(flujoEntrada);
		this.salida = new ObjectOutputStream(flujoSalida);
		
	}
	
	@Override
	public void run() {
		
		
		
		salida.close();
		entrada.close();
		flujoSalida.close();
		flujoEntrada.close();
		socket.close();
		
	}
	
	
	
	public Peticion leerPeticion() throws ClassNotFoundException, IOException {
		Peticion p;
		p = (Peticion) entrada.readObject();
		return p;
	}
	
	public void escribirPeticion(Peticion p) throws IOException {
		salida.writeObject(p);
		salida.flush();
	}
	
	
	
	
	
}
