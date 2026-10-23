package cocina.modelo;

import cocina.receta.ReceiptStep;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** Ejecuta pasos de la receta (el "procesador" de la simulación). */
public class Cocinero {
    private static final DateTimeFormatter HORA = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    private final String nombre;

    public Cocinero(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }

    public void ejecutar(ReceiptStep paso) {
        Container c = paso.getContainer();
        long inicio = System.currentTimeMillis();

        System.out.println("-------------------------------------------------");
        System.out.println("[" + LocalTime.now().format(HORA) + "] INICIO: " + paso.getDescripcion());
        System.out.println("   Cocinero  : " + nombre);
        System.out.println("   Container : " + c.getNombre());
        System.out.println("   Ingredients: " + paso.getIngredientes());
        System.out.println("   Duración simulada: " + paso.getDuracionSegundos() + " s");

        c.empezarUso();
        c.anadirIngredientes(paso.getIngredientes());

        try {
            Thread.sleep(paso.getDuracionSegundos() * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Paso interrumpido: " + paso.getDescripcion(), e);
        }

        c.terminarUso();
        long real = System.currentTimeMillis() - inicio;
        System.out.println("[" + LocalTime.now().format(HORA) + "] FIN   : " + paso.getDescripcion()
                + " -> " + c + " (real: " + real + " ms)");
    }
}
