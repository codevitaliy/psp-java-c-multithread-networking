package Principal;

import java.io.*;
import java.net.Socket;

public class Hilo extends Thread {
    private Socket socket;
    private RecursoComun rc;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private boolean continua;
    private Cliente cliente;

    public Hilo(Socket socket, RecursoComun rc) throws IOException {
        this.socket = socket;
        this.rc = rc;

        this.entrada = new ObjectInputStream(socket.getInputStream());
        this.salida = new ObjectOutputStream(socket.getOutputStream());
    }

    public void run() {
        try {
            cliente = leerMensaje();
            continua = rc.entraTienda(cliente.getId());

            if (continua) {
                Thread.sleep(2000);
                rc.saleTienda(cliente.getId());
            }

            salida.close();
            entrada.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cliente leerMensaje() throws IOException, ClassNotFoundException {
        return (Cliente) entrada.readObject();
    }
}
