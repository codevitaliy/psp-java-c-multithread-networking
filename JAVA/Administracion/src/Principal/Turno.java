package Principal;

import java.io.Serializable;

public class Turno implements Serializable {
	private static final long serialVersionUID = 1L;
	private String telefono;
	private String concepto;
	private int tipo;

	public Turno(String telefono, String concepto) {
		this.telefono = telefono;
		this.concepto = concepto;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getConcepto() {
		return concepto;
	}
	
	public int getTipo() {
		return this.tipo;
	}

	public String toString() {
		return (tipo + ":" + this.telefono + " - " + concepto);
	}
}