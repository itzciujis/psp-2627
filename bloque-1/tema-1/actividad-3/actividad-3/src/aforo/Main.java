package aforo;

import java.util.function.Supplier;

public class Main {
    private static final int VISITANTES = 4;
    private static final int ENTRADAS_POR_VISITANTE = 1000;
    private static final int REPETICIONES = 10;

    public static void main(String[] args) {
        int esperado = VISITANTES * ENTRADAS_POR_VISITANTE;

        System.out.println("===== SIN SINCRONIZACIÓN (esperado: " + esperado + ") =====");
        int fallos = ejecutarVarias(AforoSinSincronizacion::new, esperado);
        System.out.println("Ejecuciones con resultado incorrecto: " + fallos + " de " + REPETICIONES);

        System.out.println();
        System.out.println("===== CON SYNCHRONIZED (esperado: " + esperado + ") =====");
        fallos = ejecutarVarias(AforoSincronizado::new, esperado);
        System.out.println("Ejecuciones con resultado incorrecto: " + fallos + " de " + REPETICIONES);

        System.out.println();
        System.out.println("===== CON REENTRANTLOCK (esperado: " + esperado + ") =====");
        fallos = ejecutarVarias(AforoConLock::new, esperado);
        System.out.println("Ejecuciones con resultado incorrecto: " + fallos + " de " + REPETICIONES);
    }

    private static int ejecutarVarias(Supplier<Aforo> fabrica, int esperado) {
        int fallos = 0;
        for (int i = 1; i <= REPETICIONES; i++) {
            int total = ejecutar(fabrica.get());
            boolean correcto = total == esperado;
            if (!correcto) {
                fallos++;
            }
            System.out.println("Ejecución " + i + ": total = " + total + (correcto ? " (correcto)" : " (INCORRECTO)"));
        }
        return fallos;
    }

    private static int ejecutar(Aforo aforo) {
        Thread[] hilos = new Thread[VISITANTES];
        for (int i = 0; i < VISITANTES; i++) {
            hilos[i] = new Thread(new Visitante(aforo, ENTRADAS_POR_VISITANTE), "Visitante-" + (i + 1));
        }
        for (Thread hilo : hilos) {
            hilo.start();
        }
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return aforo.getTotal();
            }
        }
        return aforo.getTotal();
    }
}
