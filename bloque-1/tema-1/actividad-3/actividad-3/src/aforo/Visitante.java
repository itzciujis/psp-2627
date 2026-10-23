package aforo;

public class Visitante implements Runnable {
    private final Aforo aforo;
    private final int entradas;

    public Visitante(Aforo aforo, int entradas) {
        this.aforo = aforo;
        this.entradas = entradas;
    }

    @Override
    public void run() {
        for (int i = 0; i < entradas; i++) {
            aforo.entrar();
        }
    }
}
