package Principal;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainCliente {
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {

		Socket socket;
		Reserva reserva;
		OutputStream flujoSalida;
		ObjectOutputStream salida;
		
		for (int i = 0; i < 10; i++) {
			
			socket = new Socket("localhost", 8000);
			reserva = new Reserva ("dni"+1, (i%3) + 1);
			flujoSalida = socket.getOutputStream();
			salida = new ObjectOutputStream(flujoSalida);
			salida.writeObject(reserva);
			salida.flush();
			System.out.println("Enviado " + reserva);
			Thread.sleep(100);
			flujoSalida.close();
			salida.close();
			socket.close();
			
		}
	}
}
