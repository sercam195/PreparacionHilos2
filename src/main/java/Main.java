import java.util.ArrayList;
import java.util.Random;

public class Main {
    static ArrayList<Hilos> l = new ArrayList<>();

    public static void main(String[] args) {
        int contador = 0;
        for (int i = 1; i <= 100; i++) {
            Hilos hilo = new Hilos(i);
            hilo.start();
            l.add(hilo);
        }

        for (Hilos h : l) {
            try {
                h.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(escribirFinalistas());
        System.out.println(escribirSubCampeones());
    }

    public static String escribirFinalistas() {
        int maximo = 0;
        StringBuilder finals = new StringBuilder("\nFinalistas\n");
        for (Hilos hilo : l) {
            if (hilo.veces > maximo)
                maximo = hilo.veces;
        }
        for (Hilos hilo : l) {
            if (hilo.veces == maximo)
                finals.append("Hilo ").append(hilo.nombre).append(" se ha ejecutado ").append(hilo.veces).append(" veces\n");
        }

        return finals.toString();

    }

    public static String escribirSubCampeones() {
        int maximo1 = 0, maximo2 = 0;
        StringBuilder subcampeon = new StringBuilder("\nSubcampeones\n");
        for (Hilos hilo : l) {
            if (hilo.veces > maximo1)
                maximo1 = hilo.veces;
        }
        for (Hilos hilo : l) {
            if (hilo.veces < maximo1 && hilo.veces > maximo2)
                maximo2 = hilo.veces;
        }
        for (Hilos hilo : l) {
            if (hilo.veces == maximo2)
                subcampeon.append("Hilo ").append(hilo.nombre).append(" se ha ejecutado ").append(hilo.veces).append(" veces\n");
        }

        return subcampeon.toString();

    }
}

class Hilos extends Thread {
    Random r = new Random();
    int veces = 0;
    int nombre;

    Hilos(int nombre) {
        this.nombre = nombre;

    }

    @Override
    public void run() {
        try {
            do {
                Thread.sleep(1000);
                veces++;
            } while (r.nextBoolean());
            System.out.println("Soy el hilo " + nombre + " y he dormido " + veces + " veces");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}