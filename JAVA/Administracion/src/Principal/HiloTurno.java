package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HiloTurno extends Thread {

	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Central central;
	private Turno turno;
	
	public HiloTurno(Socket socket, Central central) throws IOException {
		
		this.socket = socket;
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(flujoEntrada);
		this.salida = new ObjectOutputStream(flujoSalida);
		this.central = central;
		
	}
	
	@Override
	public void run() {
		
		Turno turno;
		
		try {
			
		
			
			turno = leerTurno();
			
			central.agregar(turno);
			
			central.hacerPeticion();
			
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
	

	public Turno leerTurno() throws ClassNotFoundException, IOException {
		
		Turno t;
		t = (Turno) entrada.readObject();
		return t;
		
	}
	
	public void escribirTurno(Turno t) throws IOException {
		
		salida.writeObject(t);
		salida.flush();
		
	}
	
	
	
	
	
	
	
	
	
}
