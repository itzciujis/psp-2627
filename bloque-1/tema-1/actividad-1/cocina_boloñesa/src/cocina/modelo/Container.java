package cocina.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Sartén, cazuela u olla. Equivale a un "recurso" del sistema. */
public class Container {
    private final String nombre;
    private EstadoContainer estado = EstadoContainer.VACIO;
    private final List<Ingredient> contenido = new ArrayList<>();

    public Container(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }
    public EstadoContainer getEstado() { return estado; }

    public List<Ingredient> getContenido() {
        return Collections.unmodifiableList(contenido);
    }

    public void anadirIngredientes(List<Ingredient> ingredientes) {
        contenido.addAll(ingredientes);
    }

    public void vaciar() {
        contenido.clear();
        estado = EstadoContainer.VACIO;
    }

    public void empezarUso() { estado = EstadoContainer.EN_USO; }
    public void terminarUso() { estado = EstadoContainer.TERMINADO; }

    @Override
    public String toString() { return nombre + " [" + estado + "]"; }
}
