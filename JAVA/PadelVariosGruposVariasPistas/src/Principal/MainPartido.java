package Principal;

public class MainPartido {

	public static void main(String[] args) {

		GestorPadel gestor = new GestorPadel();

		Grupo[] grupos = new Grupo[20]; // 20 grupos que quieren jugar

		// Crear los hilos
		for (int i = 0; i < grupos.length; i++) {
			grupos[i] = new Grupo(gestor, i);
		}

		// Lanzar los hilos
		for (int i = 0; i < grupos.length; i++) {
			grupos[i].start();
		}

		// Esperar a que terminen
		for (int i = 0; i < grupos.length; i++) {
			try {
				grupos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("===> Todos los grupos han jugado. Fin del programa.");
	}
}
