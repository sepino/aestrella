package negocio;

public class Mapa implements Cloneable {
    private int fils;
    private int cols;

    private Punto mapa[][];

    public Mapa() {
        this.fils = 5;
        this.cols = 5;
        this.mapa = new Punto[fils][cols];
        fill();
    }

    public Mapa(int fils, int cols, Punto inicio, Punto destino) {
        this.fils = fils;
        this.cols = cols;
        this.mapa = new Punto[fils][cols];
        fill();
        colocar(inicio);
        colocar(destino);
    }

    private void fill() {
        Punto punto;
        for (int i = 0; i < fils; i++) {
            for (int j = 0; j < cols; j++) {
                mapa[i][j] = new Punto(i, j, ClasesPunto.VACIO);
            }
        }

        // TODO Buscar adyacentes cuando sea necesario.
        /*
        for (int i = 0; i < fils-1; i++) {
            for (int j = 0; j < cols-1; j++) {
                buscarAdyacentes(obtener(i, j));
            }
        }
        */
    }

    public void mostrar() {
        System.out.print("\t");
        for (int z = 0; z< cols; z++) {
            System.out.print(z + "\t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i< fils; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j< cols; j++) {
                System.out.print(mapa[i][j]);
                System.out.print("\t");
            }
            System.out.println("\n");
        }
    }

    public void colocar(Punto punto) {
        obtener(punto.getX(), punto.getY()).setClase(punto.getClase());
    }

    public Punto obtener(int x, int y) {
        return mapa[x][y];
    }

    public void buscarAdyacentes(Punto punto) {
        // Norte
        if (punto.getX() != 0) {
            punto.setAdyacente(obtener(punto.getX()-1, punto.getY()));
        }
        // Noreste
        if (punto.getX() != 0 && punto.getY() < cols-1) {
            punto.setAdyacente(obtener(punto.getX()-1, punto.getY()+1));
        }
        // Este
        if (punto.getY() < cols-1) {
            punto.setAdyacente(obtener(punto.getX(), punto.getY()+1));
        }
        // Sureste
        if (punto.getX() < fils-1 && punto.getY() < cols-1) {
            punto.setAdyacente(obtener(punto.getX()+1, punto.getY()+1));
        }
        // Sur
        if (punto.getX() < fils-1) {
            punto.setAdyacente(obtener(punto.getX()+1, punto.getY()));
        }
        // Suroeste
        if (punto.getX() < fils-1 && punto.getY() != 0) {
            punto.setAdyacente(obtener(punto.getX()+1, punto.getY()-1));
        }
        // Oeste
        if (punto.getY() != 0) {
            punto.setAdyacente(obtener(punto.getX(), punto.getY()-1));
        }
        // Noroeste
        if (punto.getX() != 0 && punto.getY() != 0) {
            punto.setAdyacente(obtener(punto.getX()-1, punto.getY()-1));
        }
    }

    @Override
    public Mapa clone() {
        Mapa clone = null;

        try {
            clone = (Mapa) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}
