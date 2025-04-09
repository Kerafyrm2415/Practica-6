import java.util.ArrayList;
import java.util.List;

public class jugadores {
    private mano manoJugador;
    private mazoCartas mazoJugador;

    public jugadores(int totalJugadores) {
        mazoJugador = new mazoCartas();
        int numCartas = 0;
        if (totalJugadores < 2){
            manoJugador = new mano(mazoJugador, 5);
        } else {
            manoJugador = new mano(mazoJugador, 7);
        }
        mostrarMazoJugador(manoJugador);
    }

    public void mostrarMazoJugador(mano jugador1) {
        System.out.println("Mano del jugador: ");
        manoJugador.mostrarMano();
    }
}
