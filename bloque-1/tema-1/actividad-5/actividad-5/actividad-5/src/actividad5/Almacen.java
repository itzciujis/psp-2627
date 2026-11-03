package actividad5;

public class Almacen {
    public static final int TOTAL_PEDIDOS = 800;
    public static final int TIEMPO_PEDIDO_MS = 25;

    private Almacen() {
    }

    public static int prepararPedido(int numeroPedido) {
        try {
            Thread.sleep(TIEMPO_PEDIDO_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Pedido " + numeroPedido + " interrumpido", e);
        }
        return 1;
    }
}
