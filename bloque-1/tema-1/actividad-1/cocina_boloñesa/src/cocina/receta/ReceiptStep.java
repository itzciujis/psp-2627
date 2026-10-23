package cocina.receta;

import cocina.modelo.Container;
import cocina.modelo.Ingredient;

import java.util.Collections;
import java.util.List;

/**
 * Paso de la receta. Solo describe QUÉ se hace, dónde y cuánto dura.
 * No decide CUÁNDO se ejecuta (eso se deja fuera para facilitar los hilos).
 */
public class ReceiptStep {
    private final String descripcion;
    private final Container container;
    private final List<Ingredient> ingredientes;
    private final int duracionSegundos;

    public ReceiptStep(String descripcion, Container container,
                       List<Ingredient> ingredientes, int duracionSegundos) {
        this.descripcion = descripcion;
        this.container = container;
        this.ingredientes = List.copyOf(ingredientes);
        this.duracionSegundos = duracionSegundos;
    }

    public String getDescripcion() { return descripcion; }
    public Container getContainer() { return container; }
    public List<Ingredient> getIngredientes() { return Collections.unmodifiableList(ingredientes); }
    public int getDuracionSegundos() { return duracionSegundos; }
}
