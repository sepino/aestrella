package es.pino.negocio;

public enum ClasesPunto {
    OBSTACULO('X'),
    VACIO('·'),
    INICIO('I'),
    DESTINO('D'),
    CAMINO('#'),
    WAYPOINT('W');

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
