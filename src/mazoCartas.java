import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class mazoCartas {
    private List<Carta> cartas;
    // Colores: Rojo, verde, azul, amarillo
    private String[] colores = {"\uD83D\uDD34", "\uD83D\uDFE9", "\uD83D\uDD35", "\uD83D\uDC9B"};

    public mazoCartas() {
        cartas = new ArrayList<>();
        crearMazo();
        barajearMazo();
    }

    private void crearMazo() {
        // Cartas numéricas (0-9)
        for (String color : colores) {
            cartas.add(new Carta(color, 0)); // Solo hay un 0 por color
            for (int i = 1; i <= 9; i++) {
                cartas.add(new Carta(color, i));
                cartas.add(new Carta(color, i)); // Dos copias de cada número
            }
        }

        // Cartas especiales con color (+2, reversa, saltar)
        for (String color : colores) {
            for (int i = 0; i < 2; i++) {
                cartas.add(new Carta(color, 10)); // +2
                cartas.add(new Carta(color, 11)); // Reversa
                cartas.add(new Carta(color, 12)); // Saltar
            }
        }

        // Cartas comodín (sin color)
        for (int i = 0; i < 4; i++) {
            cartas.add(new Carta("⬜", 13)); // Comodín normal
            cartas.add(new Carta("⬜", 14)); // Comodín +4
        }
    }

    public void mostrarCartasRestantes() {
        System.out.println("\nCartas restantes en el mazo (" + cartas.size() + "):");
        cartas.forEach(System.out::println);
    }

    public void barajearMazo() {
        Collections.shuffle(cartas);
    }

    public Carta sacarCarta() {
        return cartas.isEmpty() ? null : cartas.remove(0);
    }

    public int getCantidadCartas() {
        return cartas.size();
    }

    public List<Carta> getCartas() {
        return cartas;
    }
    public void devolverCarta(Carta carta) {
        cartas.add(carta);  // Simplemente agregamos la carta de vuelta al mazo
        barajearMazo();     // Rebarajamos el mazo para no tener la carta en un orden predecible
    }
}