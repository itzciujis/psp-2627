package recreo;

public class Alumno implements Runnable {
    private final Recreo recreo;

    public Alumno(Recreo recreo) {
        this.recreo = recreo;
    }

    @Override
    public void run() {
        recreo.esperarTimbre(Thread.currentThread().getName());
    }
}
