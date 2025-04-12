public class Tablero {
    private Carta cartaEnJuego;

    public Tablero(Carta inicial) {
        this.cartaEnJuego = inicial;
    }

    public Carta getCartaEnJuego() {
        return cartaEnJuego;
    }

    public void setCartaEnJuego(Carta carta) {
        this.cartaEnJuego = carta;
    }

    public void mostrarCartaEnJuego() {
        System.out.println("Carta en juego: " + cartaEnJuego);
    }
}