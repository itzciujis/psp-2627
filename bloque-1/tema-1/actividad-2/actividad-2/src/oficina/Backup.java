package oficina;

public class Backup implements Runnable {
    private final int ficheros;

    public Backup(int ficheros) {
        this.ficheros = ficheros;
    }

    @Override
    public void run() {
        for (int i = 1; i <= ficheros; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] Copiando fichero " + i + " de " + ficheros);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[" + Thread.currentThread().getName() + "] Interrumpido");
                return;
            }
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Backup terminado");
    }
}
