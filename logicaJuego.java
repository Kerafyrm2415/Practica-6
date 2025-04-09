import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class logicaJuego {
    private mazoCartas mazo;
    private mano manoJugador;
    private Tablero tablero;
    private Scanner scanner;
    private List<jugadores> jugadoresList;
    private int turnoActual;
    private int totalJugadores;
    boolean juegoAcabado = false;

    public logicaJuego(int numJugadores) {
        mazo = new mazoCartas();
        jugadoresList = new ArrayList<>();
        totalJugadores = numJugadores;
        turnoActual = 0;

        // Verificamos que la lista de jugadores se esté poblando correctamente
        for (int i = 0; i < totalJugadores; i++) {
            jugadoresList.add(new jugadores(mazo, totalJugadores));  // Crear jugador con el número de jugadores
        }

        Carta cartaInicial = mazo.sacarCarta();

        while (esCartaEspecial(cartaInicial)) {
            mazo.devolverCarta(cartaInicial);  // Devolver carta especial al mazo
            cartaInicial = mazo.sacarCarta();  // Sacar otra carta
        }

        tablero = new Tablero(cartaInicial);
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (!juegoAcabado) {
            jugadores jugador = jugadoresList.get(turnoActual);
            mano manoJugador = jugador.getMano();

            System.out.println("\n--------------------------------");
            System.out.println("Turno del Jugador " + (turnoActual + 1));
            tablero.mostrarCartaEnJuego();

            if (jugador.sinCartas()) {
                System.out.println("\n¡El Jugador " + (turnoActual + 1) + " ha ganado!");
                break;
            }

            if (mazo.getCantidadCartas() == 0) {
                System.out.println("\nEl mazo se ha agotado. Fin del juego.");
                juegoAcabado = true;
            }

            if (!hayCartaJugable(manoJugador)) {
                System.out.println("\nNo tienes cartas jugables. Solo puedes robar.");
                System.out.println("Presiona ENTER para robar...");
                scanner.nextLine(); // Limpiar buffer
                scanner.nextLine(); // Esperar ENTER
                robarHastaQueSePuedaJugar(manoJugador);
            } else {
                boolean cartaJugada = false;

                while (!cartaJugada) {
                    System.out.println("\n1. Jugar Carta");
                    if (!hayCartaJugable(manoJugador)) {
                        System.out.println("2. Robar Carta");
                    }
                    System.out.print("Elige una opción: ");
                    int opcion = scanner.nextInt();

                    switch (opcion) {
                        case 1:
                            mostrarCartasConIndices(manoJugador);
                            System.out.print("Selecciona el índice de la carta que quieres tirar: ");
                            int indice = scanner.nextInt();

                            if (indice >= 0 && indice < manoJugador.getCartas().size()) {
                                Carta seleccionada = manoJugador.getCartas().get(indice);

                                if (esCartaJugable(seleccionada)) {
                                    tablero.setCartaEnJuego(seleccionada);
                                    manoJugador.getCartas().remove(indice);
                                    System.out.println("Tiraste: " + seleccionada);

                                    if (jugador.sinCartas()) {
                                        System.out.println("\n¡El Jugador " + (turnoActual + 1) + " ha ganado!");
                                        return;
                                    }

                                    cartaJugada = true;
                                } else {
                                    System.out.println("Esa carta no se puede jugar.");
                                }
                            } else {
                                System.out.println("Índice inválido.");
                            }
                            break;

                        case 2:
                            System.out.println("Solo puedes robar si no tienes cartas jugables.");
                            break;

                        default:
                            System.out.println("Opción inválida.");
                    }
                }
            }

            turnoActual = (turnoActual + 1) % totalJugadores;
            procesosExtras();
        }
    }

    private void mostrarCartasConIndices(mano manoJugador) {
        int i = 0;
        for (Carta carta : manoJugador.getCartas()) {
            System.out.println(i + ": " + carta);
            i++;
        }
    }

    private boolean esCartaJugable(Carta carta) {
        Carta actual = tablero.getCartaEnJuego();
        return carta.getColor().equals(actual.getColor()) || carta.getValor() == actual.getValor();
    }

    private boolean hayCartaJugable(mano manoJugador) {
        for (Carta carta : manoJugador.getCartas()) {
            if (esCartaJugable(carta)) return true;
        }
        return false;
    }

    private void robarHastaQueSePuedaJugar(mano manoJugador) {
        boolean seEncontroCarta = false;
        while (!seEncontroCarta) {
            Carta nueva = mazo.sacarCarta();
            if (nueva == null) {
                System.out.println("El mazo se ha acabado.");
                return;
            }

            System.out.println("Robaste: " + nueva);
            manoJugador.getCartas().add(nueva);
            if (esCartaJugable(nueva))
                seEncontroCarta = true;
        }
    }

    private boolean esCartaEspecial(Carta carta) {
        return carta.getValor() == 10 || carta.getValor() == 11 || carta.getValor() == 12 || carta.getValor() == 13 || carta.getValor() == 14;
    }


    public void procesosExtras() {
        try {
            Thread.sleep(1250);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo limpiar la pantalla.");
        }
    }
}
