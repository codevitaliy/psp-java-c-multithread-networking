package Principal;

public class Main 
{
	public static void main(String[] args) throws InterruptedException 
	{
		Pista pista = new Pista(4);
		
		for (int i = 0 ;i<20;i++)
		{
			new Jugador(pista).start();
			Thread.sleep((long)(Math.random()*3000));
		}
	}
}
