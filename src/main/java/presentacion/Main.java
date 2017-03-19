package presentacion;

import negocio.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static Mapa mapa;
    private static Mapa mapaAux;
    private static int fil;
    private static int col;
    private static Punto origen;
    private static Punto destino;
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Punto> obstaculos = new ArrayList<Punto>();
    private static AEstrella aEstrella;
    private static Stack<Punto> ruta;

    public static void obtenerDatos() {
        System.out.println("Introduce Nº de filas: ");
        fil = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce Nº de columnas: ");
        col = Integer.parseInt(sc.nextLine());

        System.out.println("Introduce punto inicial (X Y): ");
        origen = new Punto(sc.nextInt(), sc.nextInt(), ClasesPunto.INICIO);
        System.out.println("Introduce punto destino (X Y): ");
        destino = new Punto(sc.nextInt(), sc.nextInt(), ClasesPunto.DESTINO);
    }

    public static void crearMapa() {
        mapa = new Mapa(fil, col, origen, destino);
    }

    public static void introducirObstaculo() {
        System.out.println("Introduce obstaculo (X Y): ");
        Punto punto = new Punto(sc.nextInt(), sc.nextInt(), ClasesPunto.OBSTACULO);
        mapa.colocar(punto);
        obstaculos.add(punto);
    }

    public static void main(String args[]) {
        Boolean next = true;

        obtenerDatos();
        crearMapa();

        do {
            System.out.println("Introducir obstaculo (S/N): ");
            if (sc.next().equalsIgnoreCase("N")) {
                next = false;
            } else {
                introducirObstaculo();
            }
        } while (next);

        while (true) {
            System.out.println("[1] Ver mapa \n[2] Introducir obstaculo \n[3] Calcular ruta \n[0] Salir");
            switch (sc.nextInt()) {
                case 1:
                    mapa.mostrar();
                    break;
                case 2:
                    introducirObstaculo();
                    break;
                case 3:
                    aEstrella = new AEstrella(mapa, origen, destino);
                    Punto punto = aEstrella.buscarRuta();
                    if (punto != null) {
                        aEstrella.crearRuta(punto, origen);
                        mapaAux = new Mapa(fil, col, origen, destino);
                        for (Punto puntoAux : aEstrella.getCamino()) {
                            puntoAux.setClase(ClasesPunto.CAMINO);
                            mapaAux.colocar(puntoAux);
                        }
                        for (Punto obstaculo : obstaculos) {
                            mapaAux.colocar(obstaculo);
                        }
                        mapaAux.mostrar();
                    } else {
                        System.out.println("No existe ningún camino al destino.");
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no válida.");
                    break;
            }
        }
    }
}
