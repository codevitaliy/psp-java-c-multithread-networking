package Principal;

public class Temporizador 
{
	private GestorAeropuerto gp;
	
	public Temporizador (GestorAeropuerto gp)
	{
		this.gp=gp;
	}
	
	public void iniciarTemporizador(int tiempo)
	{
		try 
		{
			Thread.currentThread().sleep(tiempo*1000);
		} catch (InterruptedException e) {e.printStackTrace();}
		gp.autorizarDespegue();
	}
}
