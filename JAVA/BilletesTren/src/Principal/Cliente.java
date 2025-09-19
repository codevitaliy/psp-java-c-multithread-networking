package Principal;

enum Tipo{
	CONSULTA, COMPRA
}

public class Cliente extends Thread {
	
	
	private GestorBilletes gestor;
	private int idCliente;
	private Tipo tipo;
	
	public Cliente(GestorBilletes gestor, int idCliente) {
		
		this.gestor = gestor;
		this.idCliente = idCliente;
		int numeroRandom = (int)(Math.random() * 10 +1);
		
		if(numeroRandom > 2) {
			this.tipo = Tipo.COMPRA;
		}else {
			this.tipo = Tipo.CONSULTA;
		}
		
	}
	
	
	@Override
	public void run() {
		
		switch(this.tipo) {
		case Tipo.COMPRA:
			int numRandom = (int)(Math.random() * 10 + 1);
			
			if(numRandom > 3) {
				try {
					
					gestor.comprarBilletes(this);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					Thread.sleep((int)(Math.random() * 2000 + 1));
					gestor.comprarBilletes(this);
					Thread.sleep((int)(Math.random() * 2000 + 1));
					gestor.devolverBilletes(this);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
			
		case Tipo.CONSULTA:
			
			try {
				gestor.consultarBilletes(this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	public int getIdCliente() {
		return this.idCliente;
	}
	
	

}
