package actividad6;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

/**
 * El archivo canal.txt es el mensaje entre dos procesos.
 * Mismo canal que el ejemplo 12. Cuántas tareas hay lo decide Main.
 * Aquí el mensaje es un objeto JSON. Esta clase lo convierte a texto y lo vuelve a leer.
 * Main y Trabajador no parsean el String: llaman a enviar y recibir.
 * En un sistema de verdad esto sería Redis, una cola, SQL o una llamada a otro servicio.
 */
public final class Canal {

    public static final long PAUSA_MS = 50;
    /** Los dos esperan esto antes de escribir su respuesta, para poder leer el intercambio. */
    public static final long ANTES_DE_CONTESTAR_MS = 5_000;
    public static final Path ARCHIVO = localizar();

    private Canal() {
    }

    public static void enviar(Mensaje mensaje) throws IOException {
        escribir(aJson(mensaje));
    }

    /** null si todavía no hay un objeto JSON entero. */
    public static Mensaje recibir() throws IOException {
        String texto;
        try {
            texto = leer();
        } catch (NoSuchFileException e) {
            return null;
        }
        if (texto.isEmpty()) {
            return null;
        }
        return desdeJson(texto);
    }

    private static void escribir(String mensaje) throws IOException {
        Files.createDirectories(ARCHIVO.getParent());
        // Cada proceso tiene su temporal. Si los dos usaran canal.txt.tmp,
        // uno lo renombra y el otro revienta: el archivo ya no está.
        Path temporal = ARCHIVO.resolveSibling("canal-" + ProcessHandle.current().pid() + ".tmp");
        Path cerrojo = ARCHIVO.resolveSibling("canal.lock");
        try (FileChannel canal = FileChannel.open(cerrojo, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
             FileLock candado = canal.lock()) {
            Files.writeString(temporal, mensaje + System.lineSeparator());
            publicar(temporal);
        } finally {
            Files.deleteIfExists(temporal);
        }
    }

    private static void publicar(Path temporal) throws IOException {
        try {
            Files.move(temporal, ARCHIVO, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(temporal, ARCHIVO, StandardCopyOption.REPLACE_EXISTING);
        } catch (NoSuchFileException e) {
            // En macOS el rename a veces falla si el otro proceso está leyendo canal.txt.
            if (Files.exists(temporal)) {
                Files.copy(temporal, ARCHIVO, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    private static String leer() throws IOException {
        if (!Files.exists(ARCHIVO)) {
            return "";
        }
        return Files.readString(ARCHIVO).trim();
    }

    /** Objeto JSON en una línea, sin espacios. Las comillas del texto se escapan. */
    static String aJson(Mensaje mensaje) {
        if ("fin".equals(mensaje.tipo)) {
            return "{\"tipo\":\"" + escapar(mensaje.tipo) + "\"}";
        }
        if ("ejecuta".equals(mensaje.tipo) || "trabajando".equals(mensaje.tipo)) {
            return "{\"tipo\":\"" + escapar(mensaje.tipo) + "\",\"tarea\":" + mensaje.tarea + "}";
        }
        if ("completa".equals(mensaje.tipo)) {
            return "{\"tipo\":\"" + escapar(mensaje.tipo) + "\",\"tarea\":" + mensaje.tarea
                    + ",\"resultado\":" + mensaje.resultado + "}";
        }
        throw new IllegalArgumentException("tipo desconocido: " + mensaje.tipo);
    }

    static Mensaje desdeJson(String texto) {
        Lector lector = new Lector(texto);
        lector.esperar('{');
        String tipo = null;
        Integer tarea = null;
        Long resultado = null;
        if (!lector.mira('}')) {
            boolean primero = true;
            while (!lector.mira('}')) {
                if (!primero) {
                    lector.esperar(',');
                }
                primero = false;
                String clave = lector.texto();
                lector.esperar(':');
                if ("tipo".equals(clave)) {
                    if (tipo != null) {
                        throw new IllegalArgumentException("tipo repetido");
                    }
                    tipo = lector.texto();
                } else if ("tarea".equals(clave)) {
                    if (tarea != null) {
                        throw new IllegalArgumentException("tarea repetida");
                    }
                    tarea = lector.entero();
                } else if ("resultado".equals(clave)) {
                    if (resultado != null) {
                        throw new IllegalArgumentException("resultado repetido");
                    }
                    resultado = lector.numero();
                } else {
                    throw new IllegalArgumentException("clave desconocida: " + clave);
                }
            }
        }
        lector.esperar('}');
        lector.fin();
        if (tipo == null) {
            throw new IllegalArgumentException("falta tipo");
        }
        if ("fin".equals(tipo)) {
            if (tarea != null || resultado != null) {
                throw new IllegalArgumentException("fin no lleva tarea ni resultado");
            }
            return Mensaje.fin();
        }
        if (!"ejecuta".equals(tipo) && !"trabajando".equals(tipo) && !"completa".equals(tipo)) {
            throw new IllegalArgumentException("tipo desconocido: " + tipo);
        }
        if (tarea == null) {
            throw new IllegalArgumentException("falta tarea");
        }
        if ("completa".equals(tipo)) {
            if (resultado == null) {
                throw new IllegalArgumentException("falta resultado");
            }
            return Mensaje.completa(tarea, resultado);
        }
        if (resultado != null) {
            throw new IllegalArgumentException(tipo + " no lleva resultado");
        }
        if ("ejecuta".equals(tipo)) {
            return Mensaje.ejecuta(tarea);
        }
        return Mensaje.trabajando(tarea);
    }

    private static String escapar(String valor) {
        StringBuilder salida = new StringBuilder();
        for (int i = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            if (c == '"' || c == '\\') {
                salida.append('\\');
            }
            salida.append(c);
        }
        return salida.toString();
    }

    private static Path localizar() {
        Path dir = Path.of(System.getProperty("user.dir")).toAbsolutePath();
        while (dir != null) {
            if (Files.isDirectory(dir.resolve("bloque-1/tema-1/ejemplos"))) {
                return dir.resolve("bloque-1/tema-1/actividad-6/canal.txt");
            }
            dir = dir.getParent();
        }
        // Fuera del repo de clase: el canal va en la carpeta desde la que se arranca (la del proyecto).
        return Path.of(System.getProperty("user.dir")).toAbsolutePath().resolve("canal.txt");
    }

    /** Lee un objeto JSON pequeño: claves conocidas, textos y enteros. */
    private static final class Lector {
        private final String texto;
        private int i;

        Lector(String texto) {
            this.texto = texto;
        }

        private void saltar() {
            while (i < texto.length()) {
                char c = texto.charAt(i);
                if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
                    return;
                }
                i++;
            }
        }

        boolean mira(char esperado) {
            saltar();
            return i < texto.length() && texto.charAt(i) == esperado;
        }

        void esperar(char esperado) {
            saltar();
            if (i >= texto.length() || texto.charAt(i) != esperado) {
                throw new IllegalArgumentException("JSON mal formado: " + texto);
            }
            i++;
        }

        String texto() {
            esperar('"');
            StringBuilder salida = new StringBuilder();
            while (i < texto.length()) {
                char c = texto.charAt(i++);
                if (c == '"') {
                    return salida.toString();
                }
                if (c == '\\') {
                    if (i >= texto.length()) {
                        throw new IllegalArgumentException("escape a medias");
                    }
                    char escapado = texto.charAt(i++);
                    if (escapado != '"' && escapado != '\\') {
                        throw new IllegalArgumentException("escape no soportado");
                    }
                    salida.append(escapado);
                    continue;
                }
                salida.append(c);
            }
            throw new IllegalArgumentException("falta la comilla de cierre");
        }

        int entero() {
            long valor = numero();
            if (valor < Integer.MIN_VALUE || valor > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("tarea fuera de rango");
            }
            return (int) valor;
        }

        long numero() {
            saltar();
            int inicio = i;
            if (i < texto.length() && texto.charAt(i) == '-') {
                i++;
            }
            if (i >= texto.length() || !Character.isDigit(texto.charAt(i))) {
                throw new IllegalArgumentException("esperaba un número");
            }
            while (i < texto.length() && Character.isDigit(texto.charAt(i))) {
                i++;
            }
            return Long.parseLong(texto.substring(inicio, i));
        }

        void fin() {
            saltar();
            if (i != texto.length()) {
                throw new IllegalArgumentException("sobra texto detrás del JSON");
            }
        }
    }
}
