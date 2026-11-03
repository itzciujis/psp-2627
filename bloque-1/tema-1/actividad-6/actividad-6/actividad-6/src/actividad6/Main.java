package actividad6;

import java.io.IOException;

public class Main {
    private static final int ENCARGOS = 8;
    private static final long TIEMPO_MAXIMO_MS = 30_000;

    public static void main(String[] args) throws IOException, InterruptedException {
        long[] resultados = new long[ENCARGOS];

        for (int tarea = 1; tarea <= ENCARGOS; tarea++) {
            Canal.enviar(Mensaje.ejecuta(tarea));
            System.out.println("[Main] Enviada orden: tarea " + tarea);

            boolean avisado = false;
            long inicio = System.currentTimeMillis();
            Mensaje respuesta = null;

            while (respuesta == null) {
                Mensaje mensaje = Canal.recibir();

                if (mensaje != null && mensaje.tarea == tarea && mensaje.esTrabajando() && !avisado) {
                    System.out.println("[Main] El trabajador está trabajando en la tarea " + tarea);
                    avisado = true;
                } else if (mensaje != null && mensaje.tarea == tarea && mensaje.esCompleta()) {
                    respuesta = mensaje;
                } else {
                    if (System.currentTimeMillis() - inicio > TIEMPO_MAXIMO_MS) {
                        throw new IllegalStateException("Sin respuesta a la tarea " + tarea + ". ¿Está arrancado el Trabajador?");
                    }
                    Thread.sleep(Canal.PAUSA_MS);
                }
            }

            resultados[tarea - 1] = respuesta.resultado;
            System.out.println("[Main] Guardado el resultado de la tarea " + tarea + ": " + respuesta.resultado);

            if (tarea < ENCARGOS) {
                Thread.sleep(Canal.ANTES_DE_CONTESTAR_MS);
            }
        }

        Canal.enviar(Mensaje.fin());
        System.out.println("[Main] Enviado fin");

        long suma = 0;
        System.out.println();
        System.out.println("Tarea | Resultado");
        for (int i = 0; i < ENCARGOS; i++) {
            System.out.println("  " + (i + 1) + "   |    " + resultados[i]);
            suma += resultados[i];
        }
        System.out.println("Suma  |    " + suma);
    }
}
