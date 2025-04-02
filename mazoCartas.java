import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class mazoCartas {
    private List<Carta> cartas;
    private static final String[] colores = {"Rojo", "Verde", "Azul", "Amarillo"};

    public mazoCartas() {
        cartas = new ArrayList<>();
        generarCartas();
        barajar();
    }

    private void generarCartas() {
        // Cartas numéricas (0-9)
        for (String color : colores) {
            cartas.add(new Carta(color, 0)); // Solo hay un 0 por color
            for (int i = 1; i <= 9; i++) {
                cartas.add(new Carta(color, i));
                cartas.add(new Carta(color, i)); // Dos copias de cada número
            }
        }

        // Cartas especiales (+2, reversa, bloqueo)
        for (String color : colores) {
            for (int i = 0; i < 2; i++) {
                cartas.add(new Carta(color, 10)); // +2
                cartas.add(new Carta(color, 11)); // Reversa
                cartas.add(new Carta(color, 12)); // Bloqueo
            }
        }

        // Cartas comodín (cambio de color y +4)
        for (int i = 0; i < 4; i++) {
            cartas.add(new Carta("Negro", 13)); // Comodín de cambio de color
            cartas.add(new Carta("Negro", 14)); // Comodín +4
        }
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public Carta robarCarta() {
        return cartas.isEmpty() ? null : cartas.remove(0);
    }

    public int getCantidadCartas() {
        return cartas.size();
    }

    public List<Carta> getCartas() {
        return cartas;
    }
}
