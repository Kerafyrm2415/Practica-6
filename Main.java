import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                "        \\::/____/                \\::/    /                ~~              \n" +
                "         ~~                       \\/____/                                 \n" +
                "                                                                          \n\n\n\nPractica realizada por: Derek Ramón Garzón Vizcarra\n\nIngrese el numero de jugadores:");
        int numJugadores = sc.nextInt();
        // Verificamos si hay al menos 2 jugadores
        while (numJugadores < 2) {
            System.out.println("Debe haber al menos 2 jugadores. Ingrese un número válido:");
            numJugadores = sc.nextInt();  // Leemos de nuevo el numero si es invalido
        }
        logicaJuego Uno = new logicaJuego(2);
        Uno.iniciar();
    }
}