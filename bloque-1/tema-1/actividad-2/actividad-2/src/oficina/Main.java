package oficina;

public class Main {
    public static void main(String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "] Abriendo la oficina");

        Thread impresora = new Impresora(5);
        Thread correo = new Thread(new Correo(4), "Correo");
        Thread backup = new Thread(new Backup(6), "Backup");

        impresora.start();
        correo.start();
        backup.start();

        try {
            impresora.join();
            correo.join();
            backup.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[" + Thread.currentThread().getName() + "] Interrumpido esperando a los hilos");
            return;
        }

        System.out.println("[" + Thread.currentThread().getName() + "] Oficina cerrada");
    }
}
