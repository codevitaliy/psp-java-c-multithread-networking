package Principal;
import java.io.Serializable;

enum TIPO {CONTENEDOR,ROBOT}
public class Consigna implements Serializable
{
	private static final long serialVersionUID = 1L;
	private TIPO tipo;
	private String identificador;
	private String mensaje;
	
	public Consigna (TIPO tipo,String id,String msg)
	{
		this.tipo = tipo;
		this.identificador = id;
		this.mensaje = msg;
	}
	
	public TIPO getTipo()
	{
		return this.tipo;
	}
	
	public String getMensaje()
	{
		return mensaje;
	}
	
	public void setMensaje (String mensaje)
	{
		this.mensaje = mensaje;
	}
	
	public String toString()
	{
		return tipo+" ID:"+identificador+" Mensaje:"+mensaje;
	}
}
