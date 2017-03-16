package negocio;

public enum ClasesPunto {
    OBSTACULO('X'),
    VACIO('·'),
    INICIO('I'),
    DESTINO('D'),
    CAMINO('#');

    private char simbolo;

    private ClasesPunto(char simbolo) {
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }

    @Override
    public String toString() {
        return String.valueOf(this.simbolo);
    }
}
