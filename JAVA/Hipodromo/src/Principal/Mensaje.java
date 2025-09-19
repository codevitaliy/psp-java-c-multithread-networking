package Principal;

import java.io.Serializable;

public class Mensaje implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int numero;
	private String texto;
	
	public Mensaje(int numero, String texto) {
		super();
		this.numero = numero;
		this.texto = texto;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() 
	{
		return numero+ " " + texto ;
	}
}
