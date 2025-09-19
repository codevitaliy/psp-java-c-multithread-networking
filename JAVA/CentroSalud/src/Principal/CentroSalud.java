package Principal;

public class CentroSalud {

	private boolean recepcionistaOcupado;
	private boolean pacienteHerido;
	private int colaPacienteNormal;
	private int colaPacienteHerido;

	public CentroSalud() {
		this.colaPacienteNormal = 0;
		this.colaPacienteHerido = 0;
		this.recepcionistaOcupado = false;
		this.pacienteHerido = false;
	}

	public synchronized void atencionPacienteNormal(Paciente paciente) throws InterruptedException {

		int numeroRandom;

		colaPacienteNormal++;

		System.out.println("Entra paciente Id: " + paciente.getIdPaciente() + "cola normal: " + colaPacienteNormal);

		while (recepcionistaOcupado || colaPacienteHerido > 0) {

			System.out.println("Esperando cola,id:" + paciente.getIdPaciente());

			wait();

		}

		recepcionistaOcupado = true;

		System.out.println("Atendiendo paciente id: " + paciente.getIdPaciente());

		numeroRandom = (int) (Math.random() * 2000 + 1000);
		Thread.sleep(numeroRandom);

		pacienteSale(paciente);

	}

	public synchronized void atencionPacienteHerido(Paciente paciente) throws InterruptedException {

		int numeroRandom;

		colaPacienteHerido++;

		System.out.println("Entra paciente herido id: " + paciente.getIdPaciente() + "cola heridos: " + colaPacienteHerido);

		while (pacienteHerido) {
			System.out.println("Ya hay un paciente herido siendo atendido...");
			wait();
		}

		recepcionistaOcupado = true;
		pacienteHerido = true;

		System.out.println("Atendiendo paciente herido id: " + paciente.getIdPaciente());

		numeroRandom = (int) (Math.random() * 2000 + 1000);
		Thread.sleep(numeroRandom);

		pacienteSale(paciente);

	}

	public synchronized void pacienteSale(Paciente paciente) {

		Estado estado = paciente.getEstado();

		switch (estado) {

		case NORMAL:

			System.out.println("Sale paciente normal id: " + paciente.getIdPaciente());
			colaPacienteNormal--;
			recepcionistaOcupado = false;
			notifyAll();

			break;

		case HERIDO:

			System.out.println("Sale paciente herido id: " + paciente.getIdPaciente());
			colaPacienteHerido--;
			recepcionistaOcupado = false;
			pacienteHerido = false;

			notifyAll();
			
			break;

		default:
			System.out.println("error switch centro salud");
			break;

		}

	}

}
