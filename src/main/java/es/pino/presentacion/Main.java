package es.pino.presentacion;

import es.pino.negocio.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Mapa mapa;
    private static Mapa mapaAux;
    private static int fil;
    private static int col;
    private static Punto origen;
    private static Punto destino;
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Punto> obstaculos = new ArrayList<Punto>();
    private static ArrayList<Punto> waypoints = new ArrayList<Punto>();
    private static AEstrella aEstrella;
    private static ArrayList<Punto> ruta = new ArrayList<Punto>();

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

    public static void introducirWaypoint() {
        System.out.println("Introduce waypoint (X Y): ");
        Punto punto = new Punto(sc.nextInt(), sc.nextInt(), ClasesPunto.WAYPOINT);
        mapa.colocar(punto);
        waypoints.add(punto);
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

        next = true;
        do {
            System.out.println("Introducir waypoint (S/N): ");
            if (sc.next().equalsIgnoreCase("N")) {
                next = false;
            } else {
                introducirWaypoint();
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
                    Punto origenAux = origen;
                    while (waypoints.size() > 0) {
                        aEstrella = new AEstrella(mapa, origenAux, waypoints.remove(0));
                        Punto punto = aEstrella.buscarRuta();
                        if (punto != null) {
                            aEstrella.crearRuta(punto, origenAux);
                            for (Punto puntoAux : aEstrella.getCamino()) {
                                ruta.add(puntoAux);
                            }
                        } else {
                            System.out.println("No existe ningún camino al destino.");
                        }
                        origenAux = punto;
                    }

                    aEstrella = new AEstrella(mapa, origenAux, destino);
                    Punto punto = aEstrella.buscarRuta();
                    if (punto != null) {
                        aEstrella.crearRuta(punto, origenAux);
                        mapaAux = new Mapa(fil, col, origen, destino);
                        for (Punto puntoAux : aEstrella.getCamino()) {
                            ruta.add(puntoAux);
                        }
                        for (Punto puntoRuta : ruta) {
                            puntoRuta.setClase(ClasesPunto.CAMINO);
                            mapaAux.colocar(puntoRuta);
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
