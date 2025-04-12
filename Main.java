import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numJugadores;
        int opcionJugar;
        System.out.println("\n" +
                "          _____                    _____                  _______         \n" +
                "         /\\    \\                  /\\    \\                /::\\    \\        \n" +
                "        /::\\____\\                /::\\____\\              /::::\\    \\       \n" +
                "       /:::/    /               /::::|   |             /::::::\\    \\      \n" +
                "      /:::/    /               /:::::|   |            /::::::::\\    \\     \n" +
                "     /:::/    /               /::::::|   |           /:::/~~\\:::\\    \\    \n" +
                "    /:::/    /               /:::/|::|   |          /:::/    \\:::\\    \\   \n" +
                "   /:::/    /               /:::/ |::|   |         /:::/    / \\:::\\    \\  \n" +
                "  /:::/    /      _____    /:::/  |::|   | _____  /:::/____/   \\:::\\____\\ \n" +
                " /:::/____/      /\\    \\  /:::/   |::|   |/\\    \\|:::|    |     |:::|    |\n" +
                "|:::|    /      /::\\____\\/:: /    |::|   /::\\____\\:::|____|     |:::|    |\n" +
                "|:::|____\\     /:::/    /\\::/    /|::|  /:::/    /\\:::\\    \\   /:::/    / \n" +
                " \\:::\\    \\   /:::/    /  \\/____/ |::| /:::/    /  \\:::\\    \\ /:::/    /  \n" +
                "  \\:::\\    \\ /:::/    /           |::|/:::/    /    \\:::\\    /:::/    /   \n" +
                "   \\:::\\    /:::/    /            |::::::/    /      \\:::\\__/:::/    /    \n" +
                "    \\:::\\__/:::/    /             |:::::/    /        \\::::::::/    /     \n" +
                "     \\::::::::/    /              |::::/    /          \\::::::/    /      \n" +
                "      \\::::::/    /               /:::/    /            \\::::/    /       \n" +
                "       \\::::/    /               /:::/    /              \\::/____/        \n" +
                "        \\::/____/                \\::/    /                --              \n" +
                "         --                       \\/____/                                 \n" +
                "                                                                          \n\n\n\nPractica realizada por: Derek Ramón Garzón Vizcarra");
        System.out.println("Seleccione una opción: \n 1.- Jugar \n 2.- Salir\n");
        opcionJugar = sc.nextInt();
        while (opcionJugar != 1 && opcionJugar != 2) {
            System.out.println("Opción no valida: Escriba el numero de una de las dos opciones");
            opcionJugar = sc.nextInt();
        }
        if (opcionJugar == 1) {
            System.out.println("Ingrese el numero de Jugador");
            numJugadores = sc.nextInt();
            // Verificamos si hay al menos 2 Jugador
            while (numJugadores < 2) {
                System.out.println("Debe haber al menos 2 Jugador. Ingrese un número válido:");
                numJugadores = sc.nextInt();  // Leemos de nuevo el numero si es invalido
            }
            logicaJuego Uno = new logicaJuego(numJugadores);
            Uno.limpiarPantalla();
            Uno.iniciar();
        } else {
            System.out.println("Saliendo del juego...");
        }

    }
}