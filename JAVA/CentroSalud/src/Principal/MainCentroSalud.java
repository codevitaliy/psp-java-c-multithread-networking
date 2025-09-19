package Principal;

import java.util.ArrayList;

public class MainCentroSalud {
	public static void main(String[] args) throws InterruptedException {
		
		CentroSalud centroSalud = new CentroSalud();
		
		ArrayList<Paciente> pacientes = new ArrayList<>();
		
		
		for(int i = 0; i < 15; i++) {
			
			Paciente p;
			
			if (i%2==0) {
				p = new Paciente(Estado.NORMAL,centroSalud,i);
			}else {
				p = new Paciente(Estado.HERIDO,centroSalud,i);
			}
			
			pacientes.add(p);
		}
		
		for(Paciente p : pacientes) {
			p.start();
		}
		
		for(Paciente p : pacientes) {
			p.join();
		}
		
		System.out.println("Ya no hay mas pacientes por hoy!");
		
		
		
	}
}
