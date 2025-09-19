package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class InversorRemoto extends Thread {

    private Socket socket;
    private InputStream flujoEntrada;
    private OutputStream flujoSalida;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private Parquet parquet;
    private Oferta oferta;

    public InversorRemoto(Socket socket, Parquet parquet) throws IOException {
        this.socket = socket;
        this.flujoEntrada = socket.getInputStream();
        this.flujoSalida = socket.getOutputStream();
        this.entrada = new ObjectInputStream(flujoEntrada);
        this.salida = new ObjectOutputStream(flujoSalida);
        this.parquet = parquet;
        this.oferta = null;
    }

    @Override
    public void run() {
        TIPO tipo = null;

        try {
            oferta = leerOferta();
            System.out.println("[INVERSOR] Oferta recibida: " + oferta);

            tipo = oferta.getTipo();

            switch (tipo) {
                case VENTA:
                    System.out.println("[INVERSOR] Procesando venta...");
                    parquet.vender(oferta);
                    enviarOferta(oferta);
                    break;

                case COMPRA:
                    System.out.println("[INVERSOR] Procesando compra...");
                    parquet.comprar(oferta);
                    enviarOferta(oferta);
                    break;

                default:
                    System.out.println("[INVERSOR] Tipo no reconocido.");
                    break;
            }

            salida.close();
            entrada.close();
            flujoSalida.close();
            flujoEntrada.close();
            socket.close();
            System.out.println("[INVERSOR] Conexión finalizada con " + socket.getInetAddress());

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Oferta leerOferta() throws ClassNotFoundException, IOException {
        Oferta oferta = (Oferta) entrada.readObject();
        return oferta;
    }

    public void enviarOferta(Oferta oferta) throws IOException {
        salida.writeObject(oferta);
        salida.flush();
        System.out.println("[INVERSOR] Oferta enviada: " + oferta);
    }
}
