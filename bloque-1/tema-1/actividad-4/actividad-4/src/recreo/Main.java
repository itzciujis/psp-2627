package recreo;

public class Main {
    private static final int ALUMNOS = 3;

    public static void main(String[] args) {
        demo("DEMO 1: notify()", false);
        System.out.println();
        demo("DEMO 2: notifyAll()", true);
    }

    private static void demo(String titulo, boolean usarNotifyAll) {
        System.out.println("===== " + titulo + " =====");
        Recreo recreo = new Recreo();

        Thread[] alumnos = new Thread[ALUMNOS];
        for (int i = 0; i < ALUMNOS; i++) {
            alumnos[i] = new Thread(new Alumno(recreo), "Alumno-" + (i + 1));
            alumnos[i].start();
        }

        Thread portero = new Thread(new Portero(recreo, usarNotifyAll), "Portero");
        portero.start();

        try {
            portero.join();
            Thread.sleep(1000);

            System.out.println("Alumnos que han salido tras el timbre: " + recreo.getDespertados() + " de " + ALUMNOS);

            // Limpieza: con notify() los demás alumnos seguirían en wait() para siempre
            // y el programa no terminaría, así que se despierta a todos con notifyAll().
            recreo.limpiar();

            for (Thread alumno : alumnos) {
                alumno.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
