package Principal;

import java.io.Serializable;

public class Maniobra implements Serializable {
	private static final long serialVersionUID = 1L;
	private String estado;

	public Maniobra() {}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}
}