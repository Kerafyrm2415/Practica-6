public class Main {
    public static void main(String[] args) {
        mazoCartas mazo = new mazoCartas();
        mano manoJugador = new mano(mazo);
//        System.out.println("Cartas del mazo generadas: ");
//        while (mazo.getCantidadCartas() > 0) {
//            Carta carta = mazo.robarCarta();
//            if (carta != null) {
//                System.out.println(carta.toString());
//            } else {
//                System.out.println("El mazo esta vacio");
//            }
//        }
    }
}