package negocio;

import java.util.ArrayList;
import java.util.Collections;

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

    public void removeFirst() {
        this.remove(0);
    }
}
