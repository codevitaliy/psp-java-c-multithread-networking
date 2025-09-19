package Principal;

public class RecursoComun {
    private int unidades;
    private boolean puertaOcupada;

    public RecursoComun() {
        this.unidades = 100;
        this.puertaOcupada = false;
    }

    public synchronized boolean entraTienda(int n) throws InterruptedException {
        int intentos = 0;

        System.out.println("Intenta entrar el cliente con id: " + n);
        while (puertaOcupada) {
            if (intentos == 10) {
                System.out.println("El cliente se cansa y se va: " + n);
                return false;
            }
            intentos++;
            wait();
        }

        System.out.println("Entra el cliente con id: " + n);
        puertaOcupada = true;

        if (unidades != -1) {
            unidades--;
        }

        return true;
    }

    public synchronized void saleTienda(int n) {
        if (unidades == -1) {
            System.out.println("Sale el cliente con id: " + n + " sin producto");
        } else {
            System.out.println("Sale el cliente con id: " + n + " con producto");
        }

        puertaOcupada = false;
        notifyAll();
    }
}
