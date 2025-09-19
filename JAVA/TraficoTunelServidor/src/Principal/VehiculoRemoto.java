package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class VehiculoRemoto extends Thread {

	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Control control;
	private Consigna consigna;
	private int versionEjecucion;

	public VehiculoRemoto(Socket socket, Control control) throws IOException {

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

			consigna = leerConsigna();

			control.entrar(consigna);

			escribirConsigna(consigna);

			consigna = leerConsigna();

			control.salirV5(consigna);

			consigna.setAccion("BUEN VIAJE");

			escribirConsigna(consigna);

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

	public Consigna leerConsigna() throws ClassNotFoundException, IOException {
		Consigna c;
		c = (Consigna) entrada.readObject();
		return c;
	}

	public void escribirConsigna(Consigna c) throws IOException {

		salida.writeObject(c);
		salida.flush();

	}

}
