package es.pino.negocio;

import java.util.ArrayList;

public class ListaOrdenada extends ArrayList<Punto> {

    public Punto getFirst() {
        Punto result = this.get(0);

        if (this.size() > 1) {
            for (Punto aux : this) {
                if (aux.getDistanciaF() < result.getDistanciaF()) {
                    result = aux;
                }
            }
        }

        this.remove(result);
        return result;
    }

}
