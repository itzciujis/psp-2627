package recreo;

public class Recreo {
    private boolean sonando = false;
    private int despertados = 0;

    public synchronized void esperarTimbre(String alumno) {
        System.out.println("[" + alumno + "] Esperando el timbre...");
        while (!sonando) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[" + alumno + "] Interrumpido mientras esperaba");
                return;
            }
        }
        despertados++;
        System.out.println("[" + alumno + "] Ha oído el timbre y sale al recreo");
    }

    public synchronized void tocarConNotify() {
        sonando = true;
        System.out.println("[Portero] Toca el timbre con notify()");
        notify();
    }

    public synchronized void tocarConNotifyAll() {
        sonando = true;
        System.out.println("[Portero] Toca el timbre con notifyAll()");
        notifyAll();
    }

    public synchronized void limpiar() {
        notifyAll();
    }

    public synchronized int getDespertados() {
        return despertados;
    }
}
