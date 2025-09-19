package Principal;

import java.util.ArrayList;

public class Control 
{
	private ArrayList<Consigna> consignas;
	private int capacidad;
	private int hilosEspera;
	private boolean contenedorDisponible;
	private boolean cambiarContenedor;
	
	public Control()
	{
		consignas = new ArrayList<>();
		capacidad = 0;
		hilosEspera = 0;
		cambiarContenedor=false;
		contenedorDisponible=false;
	}
	
	public synchronized void esperarContenedor() throws InterruptedException
	{
		while (!contenedorDisponible)
			wait();
	}
	
	public synchronized void producir(Consigna consigna) throws InterruptedException
	{
		int nparticulas = Integer.parseInt(consigna.getMensaje());

		hilosEspera++;
		if (nparticulas> capacidad && hilosEspera==4)
		{
			System.out.println("Solicito cambio contenedor");
			cambiarContenedor=true;
			notifyAll();
		}
	
		while (nparticulas> capacidad)
			wait();

		hilosEspera--;
		capacidad -= (nparticulas);
		notifyAll();
	}
	
	public synchronized void setCapacidad(Consigna consigna) throws InterruptedException
	{
		int capacidad = Integer.parseInt(consigna.getMensaje());
		this.capacidad = capacidad;
		this.contenedorDisponible = true;
		notifyAll();
	}
	
	public synchronized void aguardarLlenado() throws InterruptedException
	{
		while (cambiarContenedor==false)
			wait();
		cambiarContenedor=false;
	}
	
	/*public synchronized void reanudar() 
	{
		notifyAll();
	}*/
}
