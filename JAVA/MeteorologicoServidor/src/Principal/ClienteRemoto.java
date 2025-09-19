package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClienteRemoto extends Thread {

	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Servicio servicio;
	private Peticion peticion;
	
	
	public ClienteRemoto(Socket socket, Servicio servicio) throws IOException {
		
		this.socket = socket;
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(flujoEntrada);
		this.salida = new ObjectOutputStream(flujoSalida);
		this.servicio = servicio;
		
	}
	
	@Override
	public void run() {

		try {
			
			peticion = leerPeticion();
			
			servicio.registrarPeticion(peticion);
			
			escribirPeticion(peticion);
			
			peticion = leerPeticion();
			
			System.out.println(peticion.getRespuesta());
			
			salida.close();
			entrada.close();
			flujoSalida.close();
			flujoEntrada.close();
			socket.close();
			
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
