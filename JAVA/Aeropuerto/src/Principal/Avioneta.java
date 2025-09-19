package Principal;

public class Avioneta extends Thread
{
	private int identificador;
	private GestorAeropuerto ga;
	private Temporizador tp;
	
	public Avioneta (int identificador,GestorAeropuerto ga,Temporizador tp)
	{
		this.identificador = identificador;
		this.ga = ga;
		this.tp = tp;
	}
	
	public void run()
	{
		try 
		{
			ga.despegarAvioneta(identificador);
		} catch (InterruptedException e) {e.printStackTrace();}
		finally 
		{
			tp.iniciarTemporizador(2);
		}
	}
}
