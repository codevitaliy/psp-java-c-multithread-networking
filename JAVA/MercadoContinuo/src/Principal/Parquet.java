package Principal;

import java.util.ArrayList;

public class Parquet {

	ArrayList<Oferta> ofertasVendedores;
	ArrayList<Oferta> ofertasCompradores;

	public Parquet() {
		this.ofertasVendedores = new ArrayList<>();
		this.ofertasCompradores = new ArrayList<>();
	}

	// ------------------------------------------------------------------------------------------------------------------------

	public synchronized Oferta comprar(Oferta ofertaComprador) throws InterruptedException {
		System.out.println("[COMPRAR] Nueva oferta de compra: " + ofertaComprador);
		ofertasCompradores.add(ofertaComprador);

		while (encontrarVendedorV1(ofertaComprador) == null) {
			System.out.println("[COMPRAR] Esperando vendedor compatible para: " + ofertaComprador.getInversor());
			wait();
		}
		
		Oferta comprador = ofertaComprador;
		Oferta vendedor = encontrarVendedorV1(ofertaComprador);
		
		int numAcciones = 0;

		if (comprador.getAcciones() > vendedor.getAcciones()) {

			numAcciones = vendedor.getAcciones();

		} else {

			numAcciones = comprador.getAcciones();
		}
		
		Oferta operacionComprador = new Oferta(
		    TIPO.ORDEN,
		    comprador.getInversor(), // ✅ el comprador hizo esta orden
		    comprador.getEmpresa(),
		    numAcciones,
		    comprador.getPrecio()
		);

		
		Oferta operacionVendedor = new Oferta(
				TIPO.ORDEN,
				vendedor.getInversor(),
				comprador.getEmpresa(),
				numAcciones,
				comprador.getPrecio()
				);
		
		comprador.setOperacion(operacionComprador);
		vendedor.setOperacion(operacionVendedor);
		
		ofertasVendedores.remove(vendedor);
		ofertasCompradores.remove(ofertaComprador);

		System.out.println("[COMPRAR] Orden generada: " + operacionComprador);
		notifyAll();
		return operacionComprador;
	}

	// ------------------------------------------------------------------------------------------------------------------------

	public synchronized Oferta vender(Oferta ofertaVendedor) throws InterruptedException {
		System.out.println("[VENDER] Nueva oferta de venta: " + ofertaVendedor);
		ofertasVendedores.add(ofertaVendedor);

		while (encontrarCompradorV1(ofertaVendedor) == null) {
			System.out.println("[VENDER] Esperando comprador compatible para: " + ofertaVendedor.getInversor());
			wait();
		}

		Oferta vendedor = ofertaVendedor;
		Oferta comprador = encontrarCompradorV1(ofertaVendedor);

		int numAcciones = 0;

		if (comprador.getAcciones() > vendedor.getAcciones()) {
			numAcciones = vendedor.getAcciones();
		} else {
			numAcciones = comprador.getAcciones();
		}

		Oferta operacionVendedor = new Oferta(
		    TIPO.ORDEN,
		    vendedor.getInversor(), // ✅ el vendedor hizo esta orden
		    vendedor.getEmpresa(),
		    numAcciones,
		    comprador.getPrecio()
		);


		Oferta operacionComprador = new Oferta(
			TIPO.ORDEN,
			comprador.getInversor(),
			vendedor.getEmpresa(),
			numAcciones,
			comprador.getPrecio()
		);

		vendedor.setOperacion(operacionVendedor);
		comprador.setOperacion(operacionComprador);

		ofertasCompradores.remove(comprador);
		ofertasVendedores.remove(ofertaVendedor);

		System.out.println("[VENDER] Orden generada: " + operacionVendedor);
		notifyAll();
		return operacionVendedor;
	}


	// ------------------------------------------------------------------------------------------------------------------------

	public synchronized Oferta encontrarCompradorV1(Oferta ofertaVendedor) throws InterruptedException {
		for (Oferta ofertaComprador : ofertasCompradores) {
			if (ofertaVendedor.getEmpresa().equals(ofertaComprador.getEmpresa())
			    && ofertaVendedor.getPrecio() >= ofertaComprador.getPrecio()) {


				System.out.println("[BUSCAR COMPRADOR] Coincidencia encontrada: " + ofertaComprador);
				return ofertaComprador;
			}
		}
		System.out.println("[BUSCAR COMPRADOR] No se encontró comprador para: " + ofertaVendedor);
		return null;
	}

	// ------------------------------------------------------------------------------------------------------------------------

	public synchronized Oferta encontrarVendedorV1(Oferta ofertaComprador) {
		for (Oferta ofertaVendedor : ofertasVendedores) {
			if (ofertaComprador.getEmpresa().equals(ofertaVendedor.getEmpresa()) && ofertaComprador.getPrecio() <= ofertaVendedor.getPrecio()) {


				System.out.println("[BUSCAR VENDEDOR] Coincidencia encontrada: " + ofertaVendedor);
				return ofertaVendedor;
			}
		}
		System.out.println("[BUSCAR VENDEDOR] No se encontró vendedor para: " + ofertaComprador);
		return null;
	}
}
