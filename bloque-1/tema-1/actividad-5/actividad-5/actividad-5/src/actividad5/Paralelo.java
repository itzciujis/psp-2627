package actividad5;

public class Paralelo {
    private static final int HILOS = 8;

    public static void main(String[] args) {
        int pedidosPorHilo = Almacen.TOTAL_PEDIDOS / HILOS;

        Trabajador[] trabajadores = new Trabajador[HILOS];
        Thread[] hilos = new Thread[HILOS];

        long inicio = System.currentTimeMillis();

        for (int i = 0; i < HILOS; i++) {
            int primero = i * pedidosPorHilo + 1;
            int ultimo = (i + 1) * pedidosPorHilo;
            trabajadores[i] = new Trabajador(primero, ultimo);
            hilos[i] = new Thread(trabajadores[i], "Hilo-" + (i + 1));
        }

        for (Thread hilo : hilos) {
            hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[" + Thread.currentThread().getName() + "] Interrumpido esperando a los hilos");
                return;
            }
        }

        int[] resultados = new int[Almacen.TOTAL_PEDIDOS];
        for (int i = 0; i < HILOS; i++) {
            int[] tramo = trabajadores[i].getResultados();
            System.arraycopy(tramo, 0, resultados, i * pedidosPorHilo, tramo.length);
        }

        int total = 0;
        for (int resultado : resultados) {
            total += resultado;
        }

        long tiempo = System.currentTimeMillis() - inicio;

        System.out.println("[" + Thread.currentThread().getName() + "] Versión paralela (" + HILOS + " hilos)");
        System.out.println("Pedidos preparados: " + total);
        System.out.println("Tiempo: " + tiempo + " ms (" + String.format("%.2f", tiempo / 1000.0) + " s)");
    }
}
