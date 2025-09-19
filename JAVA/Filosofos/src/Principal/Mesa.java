package Principal;

public class Mesa 
{
	private boolean tenedorOcupado[];
	
	public Mesa ()
	{
		tenedorOcupado = new boolean[5];
		for (int i=0;i<5;i++)
			tenedorOcupado[i]=false;
	}
	
	public boolean isOcupado(int n)
	{
		return tenedorOcupado[n];
	}
	
	public synchronized void intentarComer (int n) throws InterruptedException
	{
		while (isOcupado(n) || isOcupado((n+1)%5))
			wait();
		tenedorOcupado[(n+1)%5]=tenedorOcupado[n]=true;
		System.out.println("El filosofo "+n+" comienza a comer.");
	}
	
	public synchronized void finalizarComer (int n)
	{
		tenedorOcupado[n]=tenedorOcupado[(n+1)%5]=false;
		notifyAll();
		System.out.println("El filosofo "+n+" termina de comer.");
	}
}
