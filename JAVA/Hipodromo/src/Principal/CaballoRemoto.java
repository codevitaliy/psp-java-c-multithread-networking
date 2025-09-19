package Principal;

import java.io.*;
import java.net.Socket;

public class CaballoRemoto extends Thread {

	private Socket socket;
	private Pista pista;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Mensaje mensaje;

	public CaballoRemoto(Socket socket, Pista pista) throws IOException {
		this.socket = socket;
		this.pista = pista;
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(flujoEntrada);
		this.salida = new ObjectOutputStream(flujoSalida);
	}

	public void run() {
		try {
			mensaje = leerMensaje(); // recibe "PREPARADO"
			pista.esperarSalida(mensaje); // espera según si está autorizado o no

			mensaje.setTexto("GO!");
			escribirMensaje(mensaje);

			mensaje = leerMensaje(); // recibe "TERMINA"
			String resultado = pista.terminarCarrera(mensaje);
			mensaje.setTexto(resultado);
			escribirMensaje(mensaje);
			salida.close();
			entrada.close();
			flujoSalida.close();
			flujoEntrada.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Mensaje leerMensaje() throws IOException, ClassNotFoundException {
		return (Mensaje) entrada.readObject();
	}

	private void escribirMensaje(Mensaje mensaje) throws IOException {
		salida.writeObject(mensaje);
		salida.flush();
	}
}
