package Principal;

public class MainGimnasio {

	public static void main(String[] args) {

		Gimnasio gimnasio = new Gimnasio();

		Abonado[] abonados = new Abonado[50];
		
		ContadorOxigeno contadorOxigeno = new ContadorOxigeno(gimnasio);
		
		contadorOxigeno.start();

		for (int i = 0; i < 50; i++) {

			abonados[i] = new Abonado(i, gimnasio);

		}

		for (int i = 0; i < 50; i++) {

			abonados[i].start();

		}

		for (int i = 0; i < 50; i++) {

			try {
				abonados[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		System.out.println("Termina main");

	}
}
