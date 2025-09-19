package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainPrueba {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(8000);
        Socket socket;
        boolean fin = false;

        RecursoComun rc = new RecursoComun();
        Hilo hilo;

        while (!fin) {
            socket = servidor.accept();
            hilo = new Hilo(socket, rc);
            hilo.start();
        }

        servidor.close();
    }
}
