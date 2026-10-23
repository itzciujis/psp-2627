package cocina.receta;

import cocina.modelo.Cocinero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Receta: lista ordenada de pasos (composición). */
public class Receipt {
    private final String nombre;
    private final List<ReceiptStep> pasos = new ArrayList<>();

    public Receipt(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }

    public void anadirPaso(ReceiptStep paso) { pasos.add(paso); }

    public List<ReceiptStep> getPasos() { return Collections.unmodifiableList(pasos); }

    /** Ejecuta todos los pasos en orden, en el hilo principal. Devuelve ms totales. */
    public long ejecutar(Cocinero cocinero) {
        System.out.println("=== Preparando: " + nombre + " (" + pasos.size() + " pasos) ===");
        long inicio = System.currentTimeMillis();
        for (ReceiptStep paso : pasos) {
            cocinero.ejecutar(paso);
        }
        long total = System.currentTimeMillis() - inicio;
        System.out.println("=================================================");
        System.out.printf("Receta completada. Tiempo total: %d ms (%.2f s)%n", total, total / 1000.0);
        return total;
    }
}
