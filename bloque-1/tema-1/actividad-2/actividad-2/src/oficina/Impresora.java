package oficina;

public class Impresora extends Thread {
    private final int paginas;

    public Impresora(int paginas) {
        super("Impresora");
        this.paginas = paginas;
    }

    @Override
    public void run() {
        for (int i = 1; i <= paginas; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] Imprimiendo página " + i + " de " + paginas);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[" + Thread.currentThread().getName() + "] Interrumpida");
                return;
            }
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Impresión terminada");
    }
}
