package Principal;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Peticion implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private LocalDate fecha;
	private LocalTime hora;
	private String localidad;
	private String id;
	private String respuesta;
	
	public Peticion (String id,String localidad)
	{
		this.id = id;
		this.localidad = localidad;
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
		this.respuesta="";
	}
	
	public String getRespuesta() 
	{
		return respuesta;
	}
	
	public void setRespuesta(String respuesta) 
	{
		this.respuesta = respuesta;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public String getLocalidad()
	{
		return this.localidad;
	}
	
	public String toString ()
	{
		String resultado="";
		resultado += "Fecha: "+fecha+" Hora: "+hora+"\n";
		resultado += "ID: "+id+" Localidad: "+localidad+"\n";
		return resultado;
	}
}
