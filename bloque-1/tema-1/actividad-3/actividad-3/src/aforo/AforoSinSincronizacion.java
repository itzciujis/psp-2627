package aforo;

public class AforoSinSincronizacion implements Aforo {
    private int entradas = 0;

    @Override
    public void entrar() {
        entradas++;
    }

    @Override
    public int getTotal() {
        return entradas;
    }
}
