public class Carta {
    private String color;
    private int valor;
    private String tipoEspecial;

    public Carta(String color, int valor) {
        this.color = color;
        this.valor = valor;
        this.tipoEspecial = determinarTipoEspecial(valor);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    private String determinarTipoEspecial(int valor) {
        switch(valor) {
            case 10: return "+2";
            case 11: return "REVERSA";
            case 12: return "SALTAR";
            case 13: return "COMODIN";
            case 14: return "+4";
            default: return "NORMAL";
        }
    }

    public String getTipoEspecial() {
        return tipoEspecial;
    }
    public boolean esEspecial() {
        return this.valor >= 10 && this.valor <= 14;  // Asumiendo que 10-14 son especiales
    }
    @Override
    public String toString() {
        if (esEspecial()) {
            if (tipoEspecial.equals("COMODIN") || tipoEspecial.equals("+4")) {
                return color + tipoEspecial;
            }
            return color + " " + tipoEspecial;
        }
        return color + " " + valor;
    }

}