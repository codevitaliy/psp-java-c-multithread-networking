package Principal;

import java.io.Serializable;

enum Tipo {VEHICULO_IZQ,VEHICULO_DCHA,AMBULANCIA}

public class Mensaje implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Tipo tipo;
	private String texto;
	
	public Mensaje(String nombre, Tipo tipo)
	{
		super();
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Tipo getTipo()
	{
		return this.tipo;
	}
	
	@Override
	public String toString() 
	{
		return nombre + " " + tipo+" "+texto ;
	}
}
