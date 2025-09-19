package Principal;

import java.io.Serializable;

public class Reserva implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String telefono;
	private int comensales;
	
	public Reserva(String telefono,int comensales)
	{
		this.telefono = telefono;
		this.comensales = comensales;
	}
	
	public int getComensales()
	{
		return comensales;
	}
	
	public String toString()
	{
		return (telefono+" -> "+comensales);
	}
}
