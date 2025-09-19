package Principal;

import java.util.ArrayList;

public class Parquet 
{
	ArrayList<Oferta> listaOfertaCompras;
	ArrayList<Oferta> listaOfertaVentas;
	
	public Parquet()
	{
		listaOfertaCompras = new ArrayList<>();
		listaOfertaVentas = new ArrayList<>();
	}
	
	public synchronized void comprar(Oferta oferta) throws InterruptedException
	{
		listaOfertaCompras.add(oferta);
		while (!encontrarVendedor(oferta))
			wait();
		listaOfertaCompras.remove(oferta);
		notifyAll();
	}
	
	public synchronized void vender(Oferta oferta) throws InterruptedException
	{
		listaOfertaVentas.add(oferta);
		while (!encontrarComprador(oferta))
			wait();
		listaOfertaVentas.remove(oferta);
		notifyAll();
	}
	
	public boolean encontrarVendedor(Oferta c)
	{
		Oferta operacionParaComprador;
		Oferta operacionParaVendedor;
		
		if (c.getOperacion()!=null)
			return true; // El vendedor no ha encontrado antes.
		// Recorremos la lista de ofertas de ventas
		for (Oferta v : listaOfertaVentas)
			if (c.getEmpresa().equals(v.getEmpresa())) // Si son acciones de la misma empresa
				if (c.getPrecio()>=v.getPrecio()) // Si el precio de compra es mayor que el de venta
					if (c.getAcciones()==v.getAcciones()) // Si el número de acciones en juego coincide
					{
						operacionParaVendedor = new Oferta(TIPO.ORDEN,c.getInversor(),
												v.getEmpresa(),v.getAcciones(),
												v.getPrecio()); // La operación se hace por el precio menor
						operacionParaComprador = new Oferta(TIPO.ORDEN,v.getInversor(),
								c.getEmpresa(),c.getAcciones(),
								v.getPrecio()); // La operación se hace por el precio menor
						c.setOperacion(operacionParaComprador);
						v.setOperacion(operacionParaVendedor);
						return true;
					}
		return false;
	}
	
	public boolean encontrarComprador(Oferta v)
	{
		Oferta operacionParaComprador;
		Oferta operacionParaVendedor;
		
		if (v.getOperacion()!=null)
			return true; // Se ha encontrado comprador
		
		for (Oferta c : listaOfertaCompras)
			if (v.getEmpresa().equals(c.getEmpresa())) // Si son acciones de la misma empresa
				if (v.getPrecio()<=c.getPrecio()) // Y el precio de compra es mayor que el de venta
					if (v.getAcciones()==c.getAcciones())
					{
						operacionParaComprador = new Oferta(TIPO.ORDEN,v.getInversor(),
												c.getEmpresa(),c.getAcciones(),
												c.getPrecio()); // La operación se hace por el precio mayor
						operacionParaVendedor = new Oferta(TIPO.ORDEN,c.getInversor(),
								v.getEmpresa(),v.getAcciones(),
								c.getPrecio()); // La operación se hace por el precio mayor
						c.setOperacion(operacionParaComprador);
						v.setOperacion(operacionParaVendedor);
						return true;
					}
		return false;
	}
	
	
	public boolean encontrarVendedorV2(Oferta c)
	{
		Oferta operacionParaComprador;
		Oferta operacionParaVendedor;
		
		if (c.getOperacion()!=null)
			return true; // El vendedor no ha encontrado antes.
		// Recorremos la lista de ofertas de ventas
		for (Oferta v : listaOfertaVentas)
			if (c.getEmpresa().equals(v.getEmpresa())) // Si son acciones de la misma empresa
				if (c.getPrecio()>=v.getPrecio()) // Si el precio de compra es mayor que el de venta
				{
				  int acciones = c.getAcciones()>v.getAcciones()?v.getAcciones():c.getAcciones();
					operacionParaVendedor = new Oferta(TIPO.ORDEN,c.getInversor(),
												v.getEmpresa(),acciones,
												v.getPrecio()); // La operación se hace por el precio menor
						operacionParaComprador = new Oferta(TIPO.ORDEN,v.getInversor(),
								c.getEmpresa(),acciones,
								v.getPrecio()); // La operación se hace por el precio menor
						c.setOperacion(operacionParaComprador);
						v.setOperacion(operacionParaVendedor);
						return true;
				}
		return false;
	}
	
	public boolean encontrarCompradorV2(Oferta v)
	{
		Oferta operacionParaComprador;
		Oferta operacionParaVendedor;
		
		if (v.getOperacion()!=null)
			return true; // Se ha encontrado comprador
		
		for (Oferta c : listaOfertaCompras)
			if (v.getEmpresa().equals(c.getEmpresa())) // Si son acciones de la misma empresa
				if (v.getPrecio()<=c.getPrecio()) // Y el precio de compra es mayor que el de venta
				{
					int acciones = (v.getAcciones()>c.getAcciones())?c.getAcciones():v.getAcciones();
					operacionParaComprador = new Oferta(TIPO.ORDEN,v.getInversor(),
												c.getEmpresa(),acciones,
												c.getPrecio()); // La operación se hace por el precio mayor
						operacionParaVendedor = new Oferta(TIPO.ORDEN,c.getInversor(),
								v.getEmpresa(),acciones,
								c.getPrecio()); // La operación se hace por el precio mayor
						c.setOperacion(operacionParaComprador);
						v.setOperacion(operacionParaVendedor);
						return true;
				}
		return false;
	}
}
