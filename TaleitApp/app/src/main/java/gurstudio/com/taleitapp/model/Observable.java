package gurstudio.com.taleitapp.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by gur on 4/23/2016.
 */
public class Observable<T> {
    private T value;
    private Collection<Observer<T>> observers;

    public Observable(T value){
        observers = new ArrayList<>();
        set(value);
    }

    public Observable(){
        this(null);
    }

    public T get(){ return value; }

    public void set(T value) {
        this.value = value;

        for (Observer<T> observer: observers){
            observer.onUpdate(value);
        }
    }

    public void addObserver(Observer<T> observer){
        observers.add(observer);
    }

    public void removeObserver(Observer<T> observer){
        observers.remove(observer);
    }
}

