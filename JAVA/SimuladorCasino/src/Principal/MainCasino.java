package Principal;

public class MainCasino {

    public static void main(String[] args) throws InterruptedException {

        Casino casino = new Casino();

        Jugador[] jugadores = new Jugador[12];

        // Crear 4 jugadores ALNUMERO
        for (int i = 0; i < 4; i++) {
            jugadores[i] = new Jugador(casino, i + 1, TipoJugador.ALNUMERO);
        }

        // Crear 4 jugadores PARIMPAR
        for (int i = 4; i < 8; i++) {
            jugadores[i] = new Jugador(casino, i + 1, TipoJugador.PARIMPAR);
        }

        // Crear 4 jugadores MARTINGALA
        for (int i = 8; i < 12; i++) {
            jugadores[i] = new Jugador(casino, i + 1, TipoJugador.MARTINGALA);
        }

        casino.empiezaPartida();

        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i].start();
        }

        while (!casino.isCasinoCerrado()) {
            casino.resultadoRuleta();
            Thread.sleep(1000); // 
             
        }

        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i].join();
        }

        System.out.println("=== EL CASINO HA CERRADO ===");
        
        System.out.println("Dinero final de los jugadores:");

        for (Jugador jugador : jugadores) {
            System.out.println("Jugador " + jugador.getIdJugador() + ": " + jugador.getDineroJugador() + " €");
        }
        
    }
}
