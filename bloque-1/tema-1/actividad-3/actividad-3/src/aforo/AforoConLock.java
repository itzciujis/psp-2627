package aforo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AforoConLock implements Aforo {
    private int entradas = 0;
    private final Lock lock = new ReentrantLock();

    @Override
    public void entrar() {
        lock.lock();
        try {
            entradas++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getTotal() {
        lock.lock();
        try {
            return entradas;
        } finally {
            lock.unlock();
        }
    }
}
