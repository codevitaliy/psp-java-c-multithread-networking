package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RemoteConvoy extends Thread {
	
	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Control control;
	private Maniobra maniobra;
	
	public RemoteConvoy(Socket socket, Control control) throws IOException {
		
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
			
			
			maniobra = leerManiobra();
			control.solicitarAcceso(maniobra);
			escribirManiobra(maniobra);
			maniobra = leerManiobra(); 
	
			Thread.sleep(5000);
			
			control.salirEstacion(maniobra);
			
			escribirManiobra(maniobra);
			
			maniobra = leerManiobra();
			
			maniobra.setEstado("BUEN_VIAJE");
			
			escribirManiobra(maniobra);
			
		
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
	
	
	
	
	
	
	
	
	public Maniobra leerManiobra() throws ClassNotFoundException, IOException {
		
		Maniobra m;
		m = (Maniobra) entrada.readObject();
		return m;
		
	}
	
	public void escribirManiobra(Maniobra m) throws IOException {
		
		salida.writeObject(m);
		salida.flush();
		
	}
	
	
	
	
}
