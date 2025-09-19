package Principal;

import java.util.ArrayList;
import java.util.Scanner;

public class Juez extends Thread {

	private Pista pista;
	private Scanner scanner;

	public Juez(Pista pista) {
		this.pista = pista;
		this.scanner = new Scanner(System.in);
	}

	public void run() {

		boolean fin = false;

		while (!false) {
			System.out.println("Introduce los caballos que van a competir:");
			String linea = scanner.nextLine();
			String[] partes = linea.split(" ");

			ArrayList<Integer> autorizados = new ArrayList<>();
			for (String s : partes) {
				autorizados.add(Integer.parseInt(s));
			}

			pista.setCaballosAutorizados(autorizados);

			System.out.println("Pulsa ENTER para dar la salida...");
			scanner.nextLine();

			pista.iniciarCarrera();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
