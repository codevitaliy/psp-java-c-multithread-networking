package Principal;

import java.awt.Color;
import java.io.Serial;
import java.io.Serializable;


public class Consigna implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Color color;
	private int tunelId;
	private String accion;
	
	public Consigna(Color color,int tunelId,String accion)
	{
		super();
		this.color = color;
		this.tunelId = tunelId;
		this.accion=accion;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getTunelId() {
		return tunelId;
	}

	public void setTunelId(int tunelId) {
		this.tunelId = tunelId;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	public String toString()
	{
		return color.toString();
	}
}
