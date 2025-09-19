package Principal;

import java.awt.Color;
import java.util.ArrayList;

public class Control {

	private int nTunel;
	private int contadorVehiculos[];
	private int contadorRojosEsperando[];
	// private ArrayList<Consigna>[] listaTunel;
	private ArrayList<Consigna> colaLlegada;
	private Consola consola;
	private String version;

	public Control(Consola consola) {
		this.nTunel = 3;
		this.contadorVehiculos = new int[nTunel];
		this.contadorRojosEsperando = new int[nTunel];
		/*
		 * this.listaTunel = new ArrayList[nTunel]; for (int i = 0; i < nTunel; i++) {
		 * listaTunel[i] = new ArrayList<>();
		 */
		this.consola = consola;
		this.colaLlegada = new ArrayList<>();

	}

	public synchronized void entrar(Consigna consigna) throws InterruptedException {

		String version = consola.getVersion();
		
		if (contadorVehiculos[0] == 0 && contadorVehiculos[1] == 0 && contadorVehiculos[2] == 0) {

			switch (version) {

			case "1":

				entrarV1(consigna);
				break;

			case "2":
				entrarV2(consigna);
				break;
			case "3":
				entrarV3(consigna);
				break;
			case "4":
				entrarV4(consigna);
				break;
			case "5":
				entrarV5(consigna);
				break;
			default:
				entrarV1(consigna);
				break;

			}

		}

	}
	
	
	public synchronized void salir(int num, Consigna consigna) throws InterruptedException {

			switch (num) {

			case 1:

				salirV1(consigna);
				break;

			case 2:
				salirV2(consigna);
				break;
			case 3:
				salirV3(consigna);
				break;
			case 4:
				salirV4(consigna);
				break;
			case 5:
				salirV5(consigna);
				break;
			default:
				salirV1(consigna);
				break;

			}

	}

//------------------------------------------------------------------------------------------------------------------------

	public synchronized void entrarV1(Consigna consigna) throws InterruptedException {

		int idTunel = consigna.getTunelId() - 1;

		contadorVehiculos[idTunel]++;

		consigna.setAccion("AUTORIZADO");
		
		int[] arrayNumeros = new int[10];
		
		

	}

//------------------------------------------------------------------------------------------------------------------------

	public synchronized void salirV1(Consigna consigna) {

		int idTunel = consigna.getTunelId() - 1;

		contadorVehiculos[idTunel]--;

		notifyAll();

	}

	// ------------------------------------------------------------------------------------------------------------------------

	public synchronized void entrarV2(Consigna consigna) throws InterruptedException {

		int idTunel = consigna.getTunelId() - 1;

		while (contadorVehiculos[idTunel] > 0) {

			wait();
		}

		contadorVehiculos[idTunel]++;

		consigna.setAccion("AUTORIZADO");

	}

	// ------------------------------------------------------------------------------------------------------------------------

	public synchronized void salirV2(Consigna consigna) {

		int idTunel = consigna.getTunelId() - 1;

		contadorVehiculos[idTunel]--;

		notifyAll();

	}

	// ------------------------------------------------------------------------------------------------------------------------

	public synchronized void entrarV3(Consigna consigna) throws InterruptedException {

		int idTunel = consigna.getTunelId() - 1;
		Color color = consigna.getColor();

		if (color.equals(Color.RED)) {
			contadorRojosEsperando[idTunel]++;
		}

		while (contadorVehiculos[idTunel] != 0 || (contadorRojosEsperando[idTunel] != 0 && !color.equals(Color.RED))) {

			wait();
		}

		if (color.equals(Color.RED)) {
			contadorRojosEsperando[idTunel]--;
		}

		contadorVehiculos[idTunel]++;

		consigna.setAccion("AUTORIZADO");

	}

//------------------------------------------------------------------------------------------------------------------------

	public synchronized void salirV3(Consigna consigna) {

		int idTunel = consigna.getTunelId() - 1;

		contadorVehiculos[idTunel]--;

		notifyAll();

	}

//------------------------------------------------------------------------------------------------------------------------

	public synchronized void entrarV4(Consigna consigna) throws InterruptedException {

		int idTunel = consigna.getTunelId() - 1;
		Color color = consigna.getColor();

		if (color.equals(Color.RED)) {
			contadorRojosEsperando[idTunel]++;
		}

		while (contadorVehiculos[idTunel] == 3 || contadorRojosEsperando[idTunel] != 0 && !color.equals(Color.RED)) {

			wait();
		}

		if (color.equals(Color.RED)) {
			contadorRojosEsperando[idTunel]--;
		}

		contadorVehiculos[idTunel]++;

		consigna.setAccion("AUTORIZADO");

	}

//------------------------------------------------------------------------------------------------------------------------

	public synchronized void salirV4(Consigna consigna) {

		int idTunel = consigna.getTunelId() - 1;

		contadorVehiculos[idTunel]--;

		notifyAll();

	}

//------------------------------------------------------------------------------------------------------------------------

	public synchronized void entrarV5(Consigna consigna) throws InterruptedException {

		int idTunel = consigna.getTunelId() - 1;

		// Añadir a la cola global de llegada
		colaLlegada.add(consigna);

		// Esperar hasta que el túnel esté libre y sea su turno en la cola
		while (contadorVehiculos[idTunel] > 0 || colaLlegada.get(0) != consigna) {
			wait();
		}

		contadorVehiculos[idTunel]++;
		consigna.setAccion("AUTORIZADO");

		/*
		 * int idTunel = consigna.getTunelId() - 1; listaTunel[idTunel].add(consigna);
		 * while (listaTunel[idTunel].get(0) != consigna || contadorVehiculos[idTunel] >
		 * 0) { wait(); } contadorVehiculos[idTunel]++;
		 * consigna.setAccion("AUTORIZADO");
		 */
	}

//------------------------------------------------------------------------------------------------------------------------

	public synchronized void salirV5(Consigna consigna) {

		int idTunel = consigna.getTunelId() - 1;

		contadorVehiculos[idTunel]--;
		colaLlegada.remove(consigna);
		notifyAll();

		/*
		 * int idTunel = consigna.getTunelId() - 1; contadorVehiculos[idTunel]--;
		 * listaTunel[idTunel].remove(consigna); notifyAll();
		 */
	}

}
