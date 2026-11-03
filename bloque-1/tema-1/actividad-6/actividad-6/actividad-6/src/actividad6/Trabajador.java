package actividad6;

import java.io.IOException;
import java.nio.file.Files;

public class Trabajador {
    private static final int FACTOR = 3;

    public static void main(String[] args) throws IOException, InterruptedException {
        Files.deleteIfExists(Canal.ARCHIVO);
        System.out.println("[Trabajador] Esperando órdenes en " + Canal.ARCHIVO);

        int ultimaTarea = 0;

        while (true) {
            Mensaje mensaje = Canal.recibir();

            if (mensaje != null && mensaje.esFin()) {
                System.out.println("[Trabajador] Recibido fin. Cierro.");
                return;
            }

            if (mensaje != null && mensaje.esEjecuta() && mensaje.tarea > ultimaTarea) {
                int tarea = mensaje.tarea;
                ultimaTarea = tarea;

                System.out.println("[Trabajador] Recibida orden: tarea " + tarea);
                System.out.println("[Trabajador] Trabajando en la tarea " + tarea + "...");
                Canal.enviar(Mensaje.trabajando(tarea));

                Thread.sleep(Canal.ANTES_DE_CONTESTAR_MS);

                long resultado = (long) tarea * FACTOR;
                Canal.enviar(Mensaje.completa(tarea, resultado));
                System.out.println("[Trabajador] Tarea " + tarea + " completa. Resultado: " + resultado);
            } else {
                Thread.sleep(Canal.PAUSA_MS);
            }
        }
    }
}
