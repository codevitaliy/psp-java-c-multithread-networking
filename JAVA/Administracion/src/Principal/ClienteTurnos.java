package Principal;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTurnos {
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {

		int opcion, i = 0;
		Socket socket;
		Turno turno;
		OutputStream FlujoDeSalida;
		ObjectOutputStream FlujoObjetoSalida;

		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("1 - Cliente Trafico");
			System.out.println("2 - Cliente Hacienda");
			System.out.println("3 - Funcionario Trafico");
			System.out.println("4 - Funcionario Hacienda");
			System.out.println("5 - Terminar");
			System.out.println("Opcion: ");
			
			opcion = sc.nextInt();
			
			switch (opcion) {
			case 1:
				turno = new Turno("Tfno-" + i, "Trafico");
				turno.setTipo(0);
				break;
			case 2:
				turno = new Turno("Tfno-" + i, "Hacienda");
				turno.setTipo(0);
				break;
			case 3:
				turno = new Turno("Tfno-" + i, "Trafico");
				turno.setTipo(1);
				break;
			case 4:
				turno = new Turno("Tfno-" + i, "Hacienda");
				turno.setTipo(1);
				break;
			default:
				turno = new Turno("none", "none");
				turno.setTipo(1);
			}
			i++;
			if (opcion > 0 && opcion < 5) {
				socket = new Socket("localhost", 9000);
				FlujoDeSalida = socket.getOutputStream();
				FlujoObjetoSalida = new ObjectOutputStream(FlujoDeSalida);
				FlujoObjetoSalida.writeObject(turno);
				FlujoObjetoSalida.flush();
				Thread.sleep(500);
				FlujoObjetoSalida.close();
				FlujoDeSalida.close();
				socket.close();
			}
		} while (opcion != 5);
		sc.close();
	}
}
