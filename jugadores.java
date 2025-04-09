import java.util.ArrayList;
import java.util.List;

public class jugadores {
    private mano manoJugador;

    public jugadores(mazoCartas mazoCompartido, int totalJugadores) {
        int numCartas = 7;
        this.manoJugador = new mano(mazoCompartido, numCartas);
    }

    public mano getMano() {
        return manoJugador;
    }

    public boolean tieneUnaCarta() {
        return manoJugador.getCartas().size() == 1;
    }

    public boolean sinCartas() {
        return manoJugador.getCartas().isEmpty();
    }

    public void mostrarMazoJugador() {
        System.out.println("Mano del jugador: ");
        manoJugador.mostrarMano();
    }
}