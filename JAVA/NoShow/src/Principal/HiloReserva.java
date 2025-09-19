package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HiloReserva extends Thread {

	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Sala sala;
	private Reserva reserva;
	
	public HiloReserva (Socket socket, Sala sala) throws IOException {
		this.socket = socket;
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(flujoEntrada);
		this.salida = new ObjectOutputStream(flujoSalida);
		this.sala = sala;
		this.reserva = null;
	}

//----------------------------------------------------------------------------------------------------------------
	
	@Override
	public void run() {
		
		
		try {
			
			reserva = leerReserva();
			sala.getMesa(reserva.getComensales());
			
			
			
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
	
	
	
	
//----------------------------------------------------------------------------------------------------------------
	
	public Reserva leerReserva() throws ClassNotFoundException, IOException {
		
		Reserva r;
		
		r = (Reserva) entrada.readObject();
		
		return r;
		
	}
	
	public void agregarReserva(Reserva r) throws IOException {
		
		salida.writeObject(r);
		salida.flush();
	}
	
	
}

