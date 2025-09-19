package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FabricaRemoto extends Thread {
	
	private Socket socket;
	private InputStream flujoEntrada;
	private OutputStream flujoSalida;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Control control;
	private Consigna consigna;
	
	//--------------------------------------------------------------------------------------------------------------------
	
	public FabricaRemoto(Socket socket, Control control) throws IOException {
		
		this.socket = socket;
		this.flujoEntrada = socket.getInputStream();
		this.flujoSalida = socket.getOutputStream();
		this.entrada = new ObjectInputStream(flujoEntrada);
		this.salida = new ObjectOutputStream(flujoSalida);
		this.control = control;
		this.consigna = null;
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------
	
	
	@Override
	public void run() {
	    try {
	        consigna = leerConsigna();
	        TIPO tipo = consigna.getTipo();

	        if (tipo == TIPO.CONTENEDOR) {
	        	while ((consigna = leerConsigna()) != null) {
	            control.setCapacidad(consigna);
	            control.aguardarLlenado();
	            salida.writeObject(new Consigna(TIPO.CONTENEDOR, "", "AVANZAR CINTA"));
	            salida.flush();
	          }
	        } 
	        else if (tipo == TIPO.ROBOT) {
	        	while ((consigna = leerConsigna()) != null) {
	            control.esperarContenedor(consigna);
	            control.producir(consigna);
	          }
	        }

	        salida.close();
	        entrada.close();
	        flujoSalida.close();
	        flujoEntrada.close();
	        socket.close();
	        
	    } catch (ClassNotFoundException | IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
	}


	
	//--------------------------------------------------------------------------------------------------------------------
	
	public Consigna leerConsigna() throws ClassNotFoundException, IOException {
		
		Consigna consigna;
		consigna = (Consigna) entrada.readObject();
		return consigna;
		
	}
	
	//--------------------------------------------------------------------------------------------------------------------

	
	public void enviarConsigna(Consigna consigna) throws IOException {
		
		salida.writeObject(consigna);
		salida.flush();
		
	}

}
