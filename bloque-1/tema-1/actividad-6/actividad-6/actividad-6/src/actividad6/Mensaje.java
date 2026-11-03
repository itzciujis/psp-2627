package actividad6;

/**
 * Un mensaje entre los dos procesos.
 * El texto JSON lo escribe y lo lee Canal. Main y Trabajador usan esta clase, no el String.
 */
public final class Mensaje {

    public final String tipo;
    /** Número de tarea. En "fin" no se usa (queda 0). */
    public final int tarea;
    /** Solo lo trae "completa". En el resto queda 0. */
    public final long resultado;

    private Mensaje(String tipo, int tarea, long resultado) {
        this.tipo = tipo;
        this.tarea = tarea;
        this.resultado = resultado;
    }

    public static Mensaje ejecuta(int tarea) {
        return new Mensaje("ejecuta", tarea, 0);
    }

    public static Mensaje trabajando(int tarea) {
        return new Mensaje("trabajando", tarea, 0);
    }

    public static Mensaje completa(int tarea, long resultado) {
        return new Mensaje("completa", tarea, resultado);
    }

    public static Mensaje fin() {
        return new Mensaje("fin", 0, 0);
    }

    public boolean esEjecuta() {
        return "ejecuta".equals(tipo);
    }

    public boolean esTrabajando() {
        return "trabajando".equals(tipo);
    }

    public boolean esCompleta() {
        return "completa".equals(tipo);
    }

    public boolean esFin() {
        return "fin".equals(tipo);
    }
}
