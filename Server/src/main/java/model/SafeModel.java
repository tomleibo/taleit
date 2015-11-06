package model;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by gur on 11/6/2015.
 */
public class SafeModel <T>{
    private final T model;
    private final ReadWriteLock lock;

    public SafeModel(T initial){
        this.model = initial;
        this.lock = new ReentrantReadWriteLock(false);
    }

    public void write(Writer<T> writer){
        try{
            lock.writeLock().lock();
            writer.write(model);
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    public <S> S read(Reader<T,S> reader){
        try{
            lock.readLock().lock();
            return reader.read(model);
        }
        finally {
            lock.readLock().unlock();
        }
    }


}

