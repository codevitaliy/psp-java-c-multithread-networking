package Principal;

public class MainPartido {

	public static void main(String[] args) {

		PistaPadel pista = new PistaPadel();

		Equipo[] arrEquipo = new Equipo[50];

		for (int i = 0; i < arrEquipo.length; i++) {

			arrEquipo[i] = new Equipo(pista, i);

		}

		for (int i = 0; i < arrEquipo.length; i++) {

			arrEquipo[i].start();

		}

		for (int i = 0; i < arrEquipo.length; i++) {

			try {
				arrEquipo[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		System.out.println("Termina el main !!!!");

	}
}
