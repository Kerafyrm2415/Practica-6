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
    private int direccionTurno = 1; // 1 para adelante, -1 para atrás (por el efecto de "Reversa")
    private boolean saltoTurno = false; // Para cartas de "Salto"

    public logicaJuego(int numJugadores) {
        mazo = new mazoCartas();
        jugadoresList = new ArrayList<>();
        totalJugadores = numJugadores;
        turnoActual = 0;

        for (int i = 0; i < totalJugadores; i++) {
            jugadoresList.add(new jugadores(mazo, totalJugadores));
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
                            try {
                                int indice = scanner.nextInt();
                                scanner.nextLine(); // Limpiar buffer

                                if (indice >= 0 && indice < manoJugador.getCartas().size()) {
                                    Carta seleccionada = manoJugador.getCartas().get(indice);

                                    if (esCartaJugable(seleccionada)) {
                                        tablero.setCartaEnJuego(seleccionada);
                                        manoJugador.getCartas().remove(indice);
                                        System.out.println("Tiraste: " + seleccionada);

                                        // Manejar cartas especiales
                                        if (seleccionada.esEspecial()) {
                                            manejarCartaEspecial(seleccionada);
                                        }

                                        if (jugador.sinCartas()) {
                                            System.out.println("\n¡El Jugador " + (turnoActual + 1) + " ha ganado!");
                                            return;
                                        }
                                        cartaJugada = true;
                                    } else {
                                        System.out.println("Índice inválido.");
                                    }
                                } else {
                                    System.out.println("Índice inválido.");
                                }
                            } catch (Exception e) {
                                System.out.println("Entrada inválida. Introduce un número.");
                                scanner.nextLine(); // Limpiar buffer
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

            // Solo avanzar el turno si no es un salto
        if (!saltoTurno) {
            avanzarTurnoBasico();
        } else {
            // Para saltos, el avance especial ya se hizo al jugar la carta
            saltoTurno = false;
        }
            procesosExtras();
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

        // Los comodines siempre se pueden jugar
        if (carta.getTipoEspecial().equals("COMODIN") || carta.getTipoEspecial().equals("+4")) {
            return true;
        }

        // Coincidencia de color o valor
        return carta.getColor().equals(actual.getColor()) ||
                carta.getValor() == actual.getValor() ||
                (carta.esEspecial() && carta.getTipoEspecial().equals(actual.getTipoEspecial()));
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
            try {
                Carta nueva = mazo.sacarCarta();
                if (nueva == null) {
                    System.out.println("El mazo se ha acabado.");
                    return;
                }

                System.out.println("Robando carta...");
                Thread.sleep(750); // Pausa de 750ms
                System.out.println("Robaste: " + nueva);
                manoJugador.getCartas().add(nueva);

                if (esCartaJugable(nueva)) {
                    seEncontroCarta = true;
                }
            } catch (InterruptedException e) {
                System.out.println("Error en la pausa: " + e.getMessage());
            }
        }
    }

    private void avanzarTurnoBasico() {
        turnoActual = (turnoActual + direccionTurno + totalJugadores) % totalJugadores;
    }

    private boolean esCartaEspecial(Carta carta) {
        return carta.getValor() == 10 || carta.getValor() == 11 || carta.getValor() == 12 || carta.getValor() == 13 || carta.getValor() == 14;
    }

    private void manejarCartaEspecial(Carta carta) {
        switch(carta.getTipoEspecial()) {
            case "+2":
                jugadorSiguienteRoba(2);
                System.out.println("¡El siguiente jugador roba 2 cartas!");
                break;

            case "REVERSA":
                direccionTurno *= -1;
                System.out.println("¡El sentido del juego ha cambiado! Dirección: " +
                        (direccionTurno == 1 ? "adelante" : "atrás"));
                break;

            case "SALTAR":
                saltoTurno = true;
                System.out.println("¡Se saltará el turno del siguiente jugador!");
                break;

            case "COMODIN":
            case "+4":
                cambiarColor(carta);
                if (carta.getTipoEspecial().equals("+4")) {
                    jugadorSiguienteRoba(4);
                    System.out.println("¡El siguiente jugador roba 4 cartas!");
                }
                break;
        }

        try {
            Thread.sleep(1500); // Pausa para leer los mensajes
        } catch (InterruptedException e) {}
    }

    private void jugadorSiguienteRoba(int cantidad) {
        int siguiente = (turnoActual + direccionTurno + totalJugadores) % totalJugadores;
        for (int i = 0; i < cantidad; i++) {
            Carta robada = mazo.sacarCarta();
            if (robada != null) {
                jugadoresList.get(siguiente).getMano().getCartas().add(robada);
            }
        }
    }

    private void avanzarTurno() {
        int jugadorAnterior = turnoActual + 1; // +1 porque los jugadores se numeran desde 1

        if (saltoTurno) {
            turnoActual = (turnoActual + direccionTurno * 2 + totalJugadores) % totalJugadores;
            saltoTurno = false;
            System.out.println("\n¡Turno saltado! Ahora es el turno del Jugador " + (turnoActual + 1));
        } else {
            turnoActual = (turnoActual + direccionTurno + totalJugadores) % totalJugadores;
        }

        // Limpiar pantalla y mostrar mensaje de cambio de turno
        procesosExtras();
        System.out.println("Turno del Jugador " + (turnoActual + 1) +
                " (anterior: Jugador " + jugadorAnterior + ")");
    }

    private void cambiarColor(Carta carta) {
        if (carta.getTipoEspecial().equals("COMODIN") || carta.getTipoEspecial().equals("+4")) {
            int opcion;
            boolean entradaValida = false;

            // Mostrar menú
            System.out.println("Elige un color:");
            System.out.println("1. Rojo \uD83D\uDD34");
            System.out.println("2. Verde \uD83D\uDFE9");
            System.out.println("3. Azul \uD83D\uDD35");
            System.out.println("4. Amarillo \uD83D\uDD36");

            // Validar entrada
            while (!entradaValida) {
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    if (opcion >= 1 && opcion <= 4) {
                        entradaValida = true;
                        String nuevoColor = "";
                        switch(opcion) {
                            case 1: nuevoColor = "\uD83D\uDD34"; break;
                            case 2: nuevoColor = "\uD83D\uDFE9"; break;
                            case 3: nuevoColor = "\uD83D\uDD35"; break;
                            case 4: nuevoColor = "\uD83D\uDD36"; break;
                        }
                        carta.setColor(nuevoColor);
                        System.out.println("El nuevo color es: " + nuevoColor);
                    } else {
                        System.out.println("Opción inválida. Elige del 1 al 4:");
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Introduce un número del 1 al 4:");
                    scanner.nextLine(); // Limpiar buffer en caso de error
                }
            }
        }
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
