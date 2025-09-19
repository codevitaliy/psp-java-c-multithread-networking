package Principal;

import java.util.Scanner;

public class Consola extends Thread 
{
	private Scanner sc;
	private Filosofo filosofo[];
	private String comando;
	
	public Consola (Filosofo[] filosofo)
	{
		this.filosofo = filosofo;
		sc = new Scanner (System.in);
	}
	
	public void run()
	{
		do
		{
			comando = sc.nextLine();
			switch (comando)
			{
				case "FIN":
					for (int i=0;i<5;i++)
						filosofo[i].detenerFilosofo();
					break;

			}
		} while (!comando.equals("FIN"));
		sc.close();
	}
}
