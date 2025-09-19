package Principal;

enum Estado {
	NORMAL, HERIDO
}

public class Paciente extends Thread {

	private Estado estado;
	private CentroSalud centroSalud;
	private int idPaciente;

	public Paciente(Estado estado, CentroSalud centroSalud, int idPaciente) {

		this.estado = estado;
		this.centroSalud = centroSalud;
		this.idPaciente = idPaciente;

	}

	@Override
	public void run() {
		
		int numeroRandom;
		
		switch (estado) {
		case NORMAL:
			try {
				
				centroSalud.atencionPacienteNormal(this);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case HERIDO:
			try {
				numeroRandom = (int) (Math.random() * 2000 + 1000);
				Thread.sleep(numeroRandom);
				centroSalud.atencionPacienteHerido(this);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("error switch hilo paciente");
			break;
		}

	}

	public Estado getEstado() {
		return this.estado;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

}