import java.util.ArrayList;
import java.util.List;

public class Mano {
    private List<Carta> mano;

    public Mano(mazoCartas mazo) {
        mano = new ArrayList<>();
        robarCartas(mazo, 7);
        // mostrarMano();
    }

    public Mano(mazoCartas mazo, int numJugadores) {
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
        mano.forEach(System.out::println);
    }

    public List<Carta> getCartas() {
        return mano;
    }
}