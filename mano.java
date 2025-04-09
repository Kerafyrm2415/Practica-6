import java.util.ArrayList;
import java.util.List;

public class mano {
    private List<Carta> mano;

    public mano(mazoCartas mazo) {
        mano = new ArrayList<>();
        robarCartas(mazo, 7);
        // mostrarMano();
    }

    public mano(mazoCartas mazo, int numJugadores) {
        mano = new ArrayList<>();
        robarCartas(mazo, numJugadores);
    }
    public void robarCartas(mazoCartas mazo, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Carta carta = mazo.sacarCarta();
            if (carta != null) {
                mano.add(carta);
            }
        }
    }

    public void mostrarMano() {
        for (Carta carta : mano) {
            System.out.println(carta);
        }
    }

    public List<Carta> getCartas() {
        return mano;
    }
}