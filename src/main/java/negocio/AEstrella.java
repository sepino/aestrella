package negocio;

import java.util.ArrayList;

public class AEstrella {
    private Mapa mapa;
    private Punto origen;
    private Punto destino;
    private ArrayList<Punto> listaCerrada;
    private ListaOrdenada listaAbierta;
    private Ruta ruta;

    public AEstrella(Mapa mapa, Punto origen, Punto destino) {
        this.mapa = mapa;
        this.origen = origen;
        this.destino = destino;

        listaCerrada = new ArrayList();
        listaAbierta = new ListaOrdenada();

        ruta = new Ruta();
    }

    public Punto buscarRuta() {
        Punto actual = null;
        origen.setDistanciaG(0);
        origen.setDistanciaH(0);
        origen.setDistanciaF(0);
        listaAbierta.add(origen);

        while (listaAbierta.size() != 0) {
            actual = listaAbierta.getFirst();
            listaCerrada.add(actual);

            if (actual.equals(destino)) {
                return actual;
            } else {
                mapa.buscarAdyacentes(actual);
                for (Punto adyacente : actual.getAdyacentes()) {
                    if (adyacente.getClase() == ClasesPunto.OBSTACULO) {
                        listaCerrada.add(actual);
                    } else {
                        if (!listaAbierta.contains(adyacente) && !listaCerrada.contains(adyacente)) {
                            setCostes(actual, adyacente);
                            adyacente.setPadre(actual);
                            listaAbierta.add(adyacente);
                        } else if (listaAbierta.contains(adyacente)) {
                            if (adyacente.getDistanciaG() < actual.getDistanciaG()) {
                                setCostes(actual, adyacente);
                                adyacente.setPadre(actual);
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    private void setCostes(Punto padre, Punto hijo) {
        double distanciaH = calcularDistanciaH(hijo, destino);
        double distanciaG = calcularDistanciaG(padre, hijo);

        hijo.setDistanciaG(distanciaG);
        hijo.setDistanciaH(distanciaH);
        hijo.setDistanciaF(distanciaG + distanciaH);
    }

    public Punto crearRuta(Punto destino, Punto origen) {
        if (destino.equals(origen)) {
            ruta.push(destino);
            return destino;
        } else {
            ruta.push(destino);
            return crearRuta(destino.getPadre(), origen);
        }
    }

    public String mostrarCamino() {
        String camino = "";

        while (!ruta.empty()) {
            Punto punto = ruta.pop();
            camino += punto.getX() + "," + punto.getY();
            if (ruta.size() > 0) {
                camino += "->";
            }
        }

        return camino;
    }

    public ArrayList<Punto> getCamino() {
        ArrayList<Punto> camino = new ArrayList<Punto>();

        while (!ruta.empty()) {
            camino.add(ruta.pop());
        }

        return camino;
    }

    private double calcularDistanciaH(Punto a, Punto b) {
        return Math.sqrt(Math.pow(Math.abs(a.getX() - b.getX()), 2) + Math.pow(Math.abs(a.getY() - b.getY()), 2));
    }

    private double calcularDistanciaG(Punto a, Punto b) {
        return a.getDistanciaG() + Math.sqrt(Math.pow(Math.abs(a.getX() - b.getX()), 2) + Math.pow(Math.abs(a.getY() - b.getY()), 2));
    }
}
