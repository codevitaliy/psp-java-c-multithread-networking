package Principal;

import java.io.Serializable;

public class Turno implements Serializable
{
	private static final long serialVersionUID = 8560552798376539405L;
	private String telefono;
	private String concepto;
	private int tipo;
	
	public Turno(String telefono,String concepto)
	{
		this.telefono = telefono;
		this.concepto = concepto;
	}
	public void setTipo(int tipo)
	{
		this.tipo = tipo;
	}
	public int getTipo()
	{
		return tipo;
	}
	public void setConcepto(String concepto)
	{
		this.concepto = concepto;
	}
	public String getConcepto()
	{
		return concepto;
	}
	public String toString()
	{
		String cadenaTipo = (tipo==0)?"Ciudadano":"Funcionario";
		return (cadenaTipo+":"+telefono+" - "+concepto);
	}
}
