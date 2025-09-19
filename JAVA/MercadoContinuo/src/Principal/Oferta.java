package Principal;

import java.io.Serializable;
import java.util.Comparator;

enum TIPO {COMPRA,VENTA,ORDEN}

public class Oferta implements Serializable,Comparable<Oferta>, Comparator<Oferta>
{
	private static final long serialVersionUID = 1L;
	private TIPO tipo;
	private String inversor;
	private String empresa;
	private int acciones;
	private double precio;
	private Oferta operacion;
	
	public Oferta(TIPO tipo, String inversor,String empresa, int acciones, double precio)
	{
		super();
		this.tipo = tipo;
		this.inversor = inversor;
		this.empresa = empresa;
		this.acciones = acciones;
		this.precio = precio;
		this.operacion = null;
	}

	public TIPO getTipo() {
		return tipo;
	}

	public void setTipo(TIPO tipo) {
		this.tipo = tipo;
	}

	public String getInversor() {
		return inversor;
	}

	public void setInversor(String inversor) {
		this.inversor = inversor;
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public int getAcciones() {
		return acciones;
	}

	public void setAcciones(int acciones) {
		this.acciones = acciones;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Oferta getOperacion() {
		return operacion;
	}

	public void setOperacion(Oferta operacion) {
		this.operacion = operacion;
	}

	@Override
	public int compareTo(Oferta o) 
	{
		return Double.compare(precio, o.precio);
	}

	@Override
	public int compare(Oferta o1, Oferta o2) 
	{
		return Double.compare(o2.precio,o1.precio);
	}
	
	public String toString()
	{
			return this.inversor+" "+this.empresa+" "+this.acciones+" "+this.precio;
	}
}
