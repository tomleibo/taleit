package lang;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by gur on 12/9/2015.
 */
public class SafeObject<T> {
    static final boolean FAIRNESS = false;
    final T value;
    final ReadWriteLock lock;

    public SafeObject(T initial){
        value = initial;
        lock = new ReentrantReadWriteLock(FAIRNESS);
    }

    public void write(Action<T> action){
        lock.writeLock().lock();
        try {
            action.perform(value);
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    public <S> S read(Function<T> function){
        lock.readLock().lock();
        try {
            return function.perform(value);
        }
        finally {
            lock.readLock().unlock();
        }
    }
}

