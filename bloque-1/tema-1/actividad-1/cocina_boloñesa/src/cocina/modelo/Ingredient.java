package cocina.modelo;

/** Ingrediente con nombre, cantidad y unidad. Inmutable. */
public class Ingredient {
    private final String nombre;
    private final double cantidad;
    private final String unidad;

    public Ingredient(String nombre, double cantidad, String unidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public String getNombre() { return nombre; }
    public double getCantidad() { return cantidad; }
    public String getUnidad() { return unidad; }

    @Override
    public String toString() {
        String c = (cantidad == Math.floor(cantidad)) ? String.valueOf((long) cantidad) : String.valueOf(cantidad);
        return nombre + " (" + c + " " + unidad + ")";
    }
}
