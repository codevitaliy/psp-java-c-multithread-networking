package Principal;

public class Filosofo extends Thread 
{
	private Mesa mesa;
	private int numero;
	private boolean fin;
	public Filosofo (int numero,Mesa mesa)
	{
		this.numero = numero;
		this.mesa = mesa;
		this.fin = false;
	}
	
	public void run()
	{
		while (!fin)
		{
			try 
			{
				mesa.intentarComer(numero);
				sleep((long) (Math.random()*4000));
				mesa.finalizarComer(numero);
				sleep((long) (Math.random()*9000));
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	public void detenerFilosofo()
	{
		fin=true;
	}
}
