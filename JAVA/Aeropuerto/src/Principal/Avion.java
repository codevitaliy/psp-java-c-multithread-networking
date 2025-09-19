package Principal;

public class Avion extends Thread 
{
	private int identificador;
	private GestorAeropuerto ga;
	private Temporizador tp;
	
	public Avion (int identificador,GestorAeropuerto ga,Temporizador tp)
	{
		this.identificador = identificador;
		this.ga = ga;
		this.tp = tp;
	}
	
	public void run()
	{
		try 
		{
			ga.despegarAvion(identificador);
		} 
		catch (InterruptedException e) {e.printStackTrace();}
		finally
		{
			tp.iniciarTemporizador(3);
		}
	}
}
