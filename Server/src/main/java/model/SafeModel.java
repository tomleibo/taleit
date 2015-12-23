package model;

import lang.Function;
import lang.Action;

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

    public void write(Action<T> action){
        try{
            lock.writeLock().lock();
            action.perform(model);
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    public <S> S read(Function<T> function){
        try{
            lock.readLock().lock();
            return function.perform(model);
        }
        finally {
            lock.readLock().unlock();
        }
    }


}

