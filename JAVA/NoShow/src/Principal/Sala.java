package Principal;

import java.util.ArrayList;

public class Sala {

    private ArrayList<Integer> disponibles;

    public Sala() {
        disponibles = new ArrayList<>();
        System.out.println("[Sala] Inicializada.");
    }

    //----------------------------------------------------------------------------------------------------

    public synchronized boolean getMesa(int nComensales) throws InterruptedException {
        System.out.println("[Sala] Cliente solicita mesa de " + nComensales + " comensales.");

        boolean mesaDisponible = false;

        for (Integer mesa : disponibles) {
            if (mesa == nComensales) {
                mesaDisponible = true;
                disponibles.remove(mesa);
                System.out.println("[Sala] Mesa de " + nComensales + " encontrada y asignada.");
                break;
            }
        }

        while (!mesaDisponible) {
            System.out.println("[Sala] No hay mesa disponible de " + nComensales + ". Esperando...");
            wait();

            for (Integer mesa : disponibles) {
                if (mesa == nComensales) {
                    mesaDisponible = true;
                    disponibles.remove(mesa);
                    System.out.println("[Sala] Mesa de " + nComensales + " asignada tras espera.");
                    break;
                }
            }
        }

        notifyAll(); // Se notifica en caso de cambios
        return mesaDisponible;
    }

    //----------------------------------------------------------------------------------------------------

    public synchronized void putMesa(int nComensales) {
        disponibles.add(nComensales);
        System.out.println("[Sala] Mesa de " + nComensales + " comensales añadida a disponibles.");
        notifyAll();
    }
}
