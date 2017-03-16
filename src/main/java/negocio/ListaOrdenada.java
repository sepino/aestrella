package negocio;

import java.util.ArrayList;
import java.util.Collections;

public class ListaOrdenada extends ArrayList {
    @Override
    public boolean add(Object o) {
        Boolean result = super.add(o);
        Collections.sort(this);

        return result;
    }

    public Punto getFirst() {
        return (Punto) this.get(0);
    }

    public void removeFirst() {
        this.remove(0);
    }
}
