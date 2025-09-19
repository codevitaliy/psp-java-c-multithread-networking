package Principal;

enum Prioridad {
	SIN, COMPRAVENTA, CONSULTA
}

public class GestorBilletes {

	private boolean gestorBloqueado;
	private int billetesDisponibles;
	private int contadorCompradores;
	private int contadorConsultores;
	private Prioridad prioridad;

	public GestorBilletes() {
		this.gestorBloqueado = false;
		this.billetesDisponibles = 10;
		this.prioridad = Prioridad.SIN;
	}

	public synchronized void comprarBilletes(Cliente cliente) throws InterruptedException {

		if (getPrioridad() == Prioridad.SIN || getPrioridad() == Prioridad.COMPRAVENTA) {

			contadorCompradores++;

			System.out.println("Nuevo comprador: " + cliente.getIdCliente() + "nCompradores: " + contadorCompradores);

			while (gestorBloqueado || billetesDisponibles == 0) {
				System.out.println("Compra bloqueada, para: " + cliente.getIdCliente() + " esperando...");
				wait();
			}

			gestorBloqueado = true;

			billetesDisponibles--;

			System.out.println(
			    "Cliente: " + cliente.getIdCliente() + " ha comprado billete, billetes disponibles: " + billetesDisponibles);

			contadorCompradores--;

			gestorBloqueado = false;

			notifyAll();

		} else if (getPrioridad() == Prioridad.CONSULTA) {

			contadorCompradores++;

			System.out.println("Nuevo comprador: " + cliente.getIdCliente() + "nCompradores: " + contadorCompradores);

			while (contadorConsultores > 0 || gestorBloqueado || billetesDisponibles == 0) {
				System.out.println("PRIO CONSULTA | Compra bloqueada, para: " + cliente.getIdCliente() + "esperando...");
				wait();
			}

			gestorBloqueado = true;

			billetesDisponibles--;

			System.out.println(
			    "Cliente: " + cliente.getIdCliente() + " ha comprado billete, billetes disponibles: " + billetesDisponibles);

			contadorCompradores--;

			gestorBloqueado = false;

			notifyAll();

		}

	}

	public synchronized void devolverBilletes(Cliente cliente) throws InterruptedException {

		if (getPrioridad() == Prioridad.SIN) {

			while (gestorBloqueado) {
				wait();
			}

			gestorBloqueado = true;

			billetesDisponibles++;
			
			System.out.println("Cliente: " + cliente.getIdCliente() + " ha decidido devolver su billete, disponibles ahora: "
			    + billetesDisponibles);
			
			gestorBloqueado = false;

			notifyAll();
			
			
		} else if (getPrioridad() == Prioridad.CONSULTA) {

			while (gestorBloqueado || contadorConsultores > 0) {
				wait();
			}

			gestorBloqueado = true;

			billetesDisponibles++;

			System.out.println("Cliente: " + cliente.getIdCliente() + " ha decidido devolver su billete, disponibles ahora: "
			    + billetesDisponibles);
			
			gestorBloqueado = false;

			notifyAll();

		}

	}

	public synchronized void consultarBilletes(Cliente cliente) throws InterruptedException {

		contadorConsultores++;

		System.out.println("Nuevo consultor: " + cliente.getIdCliente() + " nConsultores: " + contadorConsultores);


		if (getPrioridad() == Prioridad.SIN || getPrioridad() == Prioridad.CONSULTA) {

			while (gestorBloqueado) {

				System.out.println(" Consulta bloqueada, para: " + cliente.getIdCliente() + "esperando...");
				wait();

			}

			gestorBloqueado = true;

			System.out.println("Nuevo consultor: " + cliente.getIdCliente() + " consulta plazas: " + billetesDisponibles);
			
			contadorConsultores--;
			
			gestorBloqueado = false;

			notifyAll();

		} else if (getPrioridad() == Prioridad.COMPRAVENTA) {

			while (contadorCompradores > 0 || gestorBloqueado) {

				System.out.println("COMPRAVENTA PRIO | Consulta bloqueada, para: " + cliente.getIdCliente() + "esperando...");
				wait();

			}
		

			gestorBloqueado = true;

			System.out.println("Nuevo consultor: " + cliente.getIdCliente() + " consulta plazas: " + billetesDisponibles);
			
			contadorConsultores--;
			
			gestorBloqueado = false;

			notifyAll();

		}

	}

	public Prioridad getPrioridad() {
		return this.prioridad;
	}

}
