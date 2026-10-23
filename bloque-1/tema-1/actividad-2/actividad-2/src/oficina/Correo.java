package oficina;

public class Correo implements Runnable {
    private final int mensajes;

    public Correo(int mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public void run() {
        for (int i = 1; i <= mensajes; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] Revisando mensaje " + i + " de " + mensajes);
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[" + Thread.currentThread().getName() + "] Interrumpido");
                return;
            }
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Correo revisado");
    }
}
