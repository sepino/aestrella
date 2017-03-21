package es.pino.negocio;

import java.util.ArrayList;

public class Punto implements Comparable<Punto> {
    private int x;
    private int y;
    private Punto padre;
    private ClasesPunto clase;
    private ArrayList<Punto> adyacentes;
    private double distanciaG;
    private double distanciaH;
    private double distanciaF;

    public Punto() {
        adyacentes = new ArrayList<Punto>();
    }

    public Punto(int x, int y, ClasesPunto clase) {
        this.x = x;
        this.y = y;
        this.clase = clase;
        adyacentes = new ArrayList<Punto>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ClasesPunto getClase() {
        return this.clase;
    }

    public void setClase(ClasesPunto clase) {
        this.clase = clase;
    }

    public Punto getPadre() {
        return padre;
    }

    public void setPadre(Punto padre) {
        this.padre = padre;
    }

    public ArrayList<Punto> getAdyacentes() {
        return this.adyacentes;
    }

    public void setAdyacente(Punto adyacente) {
        this.adyacentes.add(adyacente);
    }

    public double getDistanciaG() {
        return distanciaG;
    }

    public void setDistanciaG(double distanciaG) {
        this.distanciaG = distanciaG;
    }

    public double getDistanciaH() {
        return distanciaH;
    }

    public void setDistanciaH(double distanciaH) {
        this.distanciaH = distanciaH;
    }

    public double getDistanciaF() {
        return distanciaF;
    }

    public void setDistanciaF(double distanciaF) {
        this.distanciaF = distanciaF;
    }

    public float distanceTo(Punto destino) {
        float dx = destino.getX() - this.x;
        float dy = destino.getY() - this.y;

        return (float) Math.sqrt( (dx*dx) + (dy*dy) );
    }

    public boolean equals(Punto b) {
        return x == b.getX() && y == b.getY();
    }

    @Override
    public String toString() {
        return String.valueOf(clase);
    }

    public int compareTo(Punto o) {
        if (this.getDistanciaF() < o.getDistanciaF()) {
            return -1;
        } else if (this.getDistanciaF() == o.getDistanciaF()){
            return 0;
        } else {
            return 1;
        }
    }
}
