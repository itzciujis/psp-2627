package actividad5;

public class Trabajador implements Runnable {
    private final int primerPedido;
    private final int ultimoPedido;
    private final int[] resultados;

    public Trabajador(int primerPedido, int ultimoPedido) {
        this.primerPedido = primerPedido;
        this.ultimoPedido = ultimoPedido;
        this.resultados = new int[ultimoPedido - primerPedido + 1];
    }

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Pedidos " + primerPedido + " a " + ultimoPedido);
        for (int pedido = primerPedido; pedido <= ultimoPedido; pedido++) {
            resultados[pedido - primerPedido] = Almacen.prepararPedido(pedido);
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Tramo terminado");
    }

    public int[] getResultados() {
        return resultados;
    }
}
