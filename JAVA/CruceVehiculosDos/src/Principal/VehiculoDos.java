package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class VehiculoDos extends Thread {

	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private ControlTrafico control;
	private Mensaje mensaje;
	
	public VehiculoDos(Socket socket, ControlTrafico control) throws IOException {
		
		this.socket = socket;
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(flujoEntrada);
		this.salida = new ObjectOutputStream(flujoSalida);
		this.control = control;
				
	}
	
	@Override
	public void run() {
		
		try {
			
			Tipo tipo;
			
			mensaje = leerMensaje();
			
			tipo = mensaje.getTipo();
			
			control.registrarVehiculo(mensaje);
			
			control.entraVehiculo(mensaje);
			
			escribirMensaje(mensaje);
			
			mensaje = leerMensaje();
			
			control.salirCruce(mensaje);
			
			mensaje.setTexto("Buen Viaje");
			 
			escribirMensaje(mensaje);
			
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
	
	
	
	
	public Mensaje leerMensaje() throws ClassNotFoundException, IOException {
		Mensaje m;
		m = (Mensaje) entrada.readObject();
		return m;
	}
	
	public void escribirMensaje(Mensaje m ) throws IOException {
		
		salida.writeObject(m);
		salida.flush();
		
	}
 	
	
	
	
	
}
