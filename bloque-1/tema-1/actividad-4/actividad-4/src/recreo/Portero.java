package recreo;

public class Portero implements Runnable {
    private final Recreo recreo;
    private final boolean usarNotifyAll;

    public Portero(Recreo recreo, boolean usarNotifyAll) {
        this.recreo = recreo;
        this.usarNotifyAll = usarNotifyAll;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        if (usarNotifyAll) {
            recreo.tocarConNotifyAll();
        } else {
            recreo.tocarConNotify();
        }
    }
}
