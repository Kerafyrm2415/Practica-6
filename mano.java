import java.util.ArrayList;
import java.util.List;

public class mano {
    private List<Carta> mano;

    public mano(mazoCartas mazo) {
        mano = new ArrayList<>();
        robarCartas(mazo, 7);
        mostrarMano();
    }

    public void robarCartas(mazoCartas mazo, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Carta carta = mazo.robarCarta();
            if (carta != null) {
                mano.add(carta);
            }
        }
    }

    public void mostrarMano() {
        System.out.println("Mano del jugador:");
        for (Carta carta : mano) {
            System.out.println(carta);
        }
    }
}