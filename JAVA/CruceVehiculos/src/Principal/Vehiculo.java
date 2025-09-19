package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Vehiculo extends Thread {
	
	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Cruce cruce;
	private Mensaje mensaje;
	
	public Vehiculo (Socket socket, Cruce cruce) throws IOException {
		
		this.socket = socket;
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(flujoEntrada);
		this.salida = new ObjectOutputStream(flujoSalida);
		this.cruce = cruce;
		this.mensaje = null;
		
		
		
		
	}
	
	@Override
	public void run() {
		
		Tipo tipo;
		
		
		try {
			mensaje = leerMensaje();
			
			tipo = mensaje.getTipo();
			
			cruce.entrarCruce(tipo);
			
			mensaje.setTexto("Acceso autorizado");
			
			escribirMensaje(mensaje);
			
			
			mensaje = leerMensaje();
			
			cruce.salirCruce(tipo);
			
			mensaje.setTexto("Buen viaje");
			
			escribirMensaje(mensaje);
			
			salida.close();
			entrada.close();
			flujoSalida.close();
			flujoEntrada.close();
			socket.close();
			
			
			
		} catch (ClassNotFoundException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		}
	
	
	
	public Mensaje leerMensaje() throws ClassNotFoundException, IOException {
		
		Mensaje m;
		
		m = (Mensaje) entrada.readObject();
		
		return m;
		
	}
	
	
	
	public void escribirMensaje(Mensaje m) throws IOException {
		
		salida.writeObject(m);
		salida.flush();
		
	}
	
	

	

}
