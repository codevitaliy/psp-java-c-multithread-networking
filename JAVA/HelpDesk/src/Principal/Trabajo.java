package Principal;

import java.io.Serializable;

public class Trabajo implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String identificacion;
	private int tipo; // 0 Cliente, 1 Técnico
	private String descripcion;
	private String estado;
	private String solucion;
	
	public Trabajo (String identificacion, int tipo,String descripcion)
	{
		this.identificacion = identificacion;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.estado = "";
		this.solucion = "";
	}
	
	public String toString()
	{
		return "Problema: "+descripcion+" Estado: "+estado+" Solución: "+solucion;
	}
	
	public String getIdentificacion()
	{
		return identificacion;
	}
	
	public int getTipo()
	{
		return tipo;
	}
	
	public String getEstado()
	{
		return estado;
	}
	
	public void setSolucion(String solucion)
	{
		this.solucion = solucion;
	}
	
	public String getSolucion()
	{
		return solucion;
	}
	
	public void setEstado(String estado)
	{
		this.estado=estado;
	}
}

