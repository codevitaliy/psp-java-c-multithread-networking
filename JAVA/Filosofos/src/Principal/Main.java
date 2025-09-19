package Principal;

public class Main 
{
	public static void main(String[] args) 
	{
		Mesa mesa = new Mesa();
		Filosofo filosofos[];
		Consola consola;
		
		filosofos=new Filosofo[5];
		consola = new Consola(filosofos);
		consola.start();
		
		for (int i=0;i<5;i++)
		{
			filosofos[i] = new Filosofo(i,mesa);
			filosofos[i].start();
		}
	}

}
