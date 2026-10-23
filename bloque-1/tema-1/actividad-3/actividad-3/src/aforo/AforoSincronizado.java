package aforo;

public class AforoSincronizado implements Aforo {
    private int entradas = 0;

    @Override
    public synchronized void entrar() {
        entradas++;
    }

    @Override
    public synchronized int getTotal() {
        return entradas;
    }
}
