package Principal;

public class Pista 
{
	private int capacidad;
	private int ocupacion;
	private boolean pistaOcupada;
	
	private int njugando;
	
	public Pista (int n)
	{
		this.capacidad = n;
		this.ocupacion = 0;
		this.pistaOcupada = false;
		this.njugando = 0;
	}
	
	public void jugar() throws InterruptedException
	{
		while (ocupacion==capacidad ||
				pistaOcupada==true)
			wait();
		ocupacion++;
		if (ocupacion==capacidad)
			pistaOcupada = true;
	}
	
	public void jugando() throws InterruptedException
	{
		njugando++;
		if (njugando<capacidad)
			wait();
		else
		{
			Thread.sleep((long)(2000+Math.random()*5000));
			notifyAll();
		}
	}
	
	public void salir()
	{
		ocupacion--;
		if (ocupacion==0)
		{
			pistaOcupada = false;
			notifyAll();
		}
	}
	
	
}
