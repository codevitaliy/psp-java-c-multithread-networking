package Principal;

public class Main 
{
	public static void main(String[] args) throws InterruptedException 
	{
		GestorAeropuerto gp = new GestorAeropuerto();
		Temporizador tp = new Temporizador(gp);
		
		for (int i=0;i<8;i++)
		{
			if (Math.random()<0.3)
				new Avioneta(i,gp,tp).start();
			else
				new Avion(i,gp,tp).start();
			
			Thread.sleep((long)(Math.random()*3000));
		}
	}
}
