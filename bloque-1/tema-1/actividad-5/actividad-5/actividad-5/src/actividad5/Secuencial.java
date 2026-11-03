package actividad5;

public class Secuencial {
    public static void main(String[] args) {
        int[] resultados = new int[Almacen.TOTAL_PEDIDOS];

        long inicio = System.currentTimeMillis();

        for (int pedido = 1; pedido <= Almacen.TOTAL_PEDIDOS; pedido++) {
            resultados[pedido - 1] = Almacen.prepararPedido(pedido);
        }

        int total = 0;
        for (int resultado : resultados) {
            total += resultado;
        }

        long tiempo = System.currentTimeMillis() - inicio;

        System.out.println("[" + Thread.currentThread().getName() + "] Versión secuencial");
        System.out.println("Pedidos preparados: " + total);
        System.out.println("Tiempo: " + tiempo + " ms (" + String.format("%.2f", tiempo / 1000.0) + " s)");
    }
}
