package Principal;

public class Jugador extends Thread
{
	private Pista pista;
	
	public Jugador(Pista pista)
	{
		this.pista = pista;
	}
	
	public void run()
	{
		try 
		{
			pista.jugar();
			pista.jugando();			
			pista.salir();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
