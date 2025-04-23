public class Jugador {
    private Mano manoJugador;

    public Jugador(mazoCartas mazoCompartido, int totalJugadores) {
        int numCartas = 7;
        this.manoJugador = new Mano(mazoCompartido, numCartas);
    }

    public Mano getMano() {
        return manoJugador;
    }

    public boolean tieneUnaCarta() {
        return manoJugador.getCartas().size() == 1;
    }

    public boolean sinCartas() {
        return manoJugador.getCartas().isEmpty();
    }

    public void mostrarMazoJugador() {
        System.out.println("Mano del jugador: ");
        manoJugador.mostrarMano();
    }
}