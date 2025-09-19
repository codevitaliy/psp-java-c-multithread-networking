package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TrabajoRemoto extends Thread {

	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private HelpDesk helpDesk;
	private Trabajo trabajo;

	public TrabajoRemoto(Socket socket, HelpDesk helpDesk) throws IOException {

		this.socket = socket;
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(flujoEntrada);
		this.salida = new ObjectOutputStream(flujoSalida);
		this.helpDesk = helpDesk;

	}

	@Override
	public void run() {

		Trabajo trabajo;

		try {

			trabajo = leerTrabajo();
			int tipo = trabajo.getTipo();
			helpDesk.agregarTrabajos(trabajo);

			switch (tipo) {

			case 0:
					
				helpDesk.buscarTecnico(trabajo);
				

				break;

			case 1:
				
				helpDesk.buscarCliente(trabajo);

				break;

			default:

				break;

			}

		} catch (ClassNotFoundException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Trabajo leerTrabajo() throws ClassNotFoundException, IOException {

		Trabajo t;
		t = (Trabajo) entrada.readObject();
		return t;

	}

	public void escribirTrabajo(Trabajo t) throws IOException {

		salida.writeObject(t);
		salida.flush();

	}

}
