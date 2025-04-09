import java.util.ArrayList;
import java.util.List;

public class jugadores {
    private mano manoJugador;

    public jugadores(mazoCartas mazoCompartido, int totalJugadores) {
        int numCartas = totalJugadores < 2 ? 5 : 7;
        this.manoJugador = new mano(mazoCompartido, numCartas);
    }

    public mano getMano() {
        return manoJugador;
    }

    public boolean sinCartas() {
        return manoJugador.getCartas().isEmpty();
    }

    public void mostrarMazoJugador() {
        System.out.println("Mano del jugador: ");
        manoJugador.mostrarMano();
    }
}