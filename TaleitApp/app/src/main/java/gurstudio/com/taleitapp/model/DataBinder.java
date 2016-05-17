package gurstudio.com.taleitapp.model;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by gur on 4/23/2016.
 */
public class DataBinder extends HashMap<Observer, Observable>{

    @Override
    public void clear(){
        for (Observer observer: keySet()){
            get(observer).removeObserver(observer);
        }

        super.clear();
    }

    @Override
    public Observable put(final Observer observer, Observable observable){
        observable.addObserver(new Observer() {
            @Override
            public void onUpdate(final Object value) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        observer.onUpdate(value);
                    }
                });
            }
        });

        return super.put(observer, observable);
    }

    public void bind(final TextView view, final ObservableText text){
        put(new Observer<CharSequence>() {
            @Override
            public void onUpdate(CharSequence value) {
                view.setText(value);
            }
        }, text);
    }

    public void bind(final TextView view, final ObservableNumber observable){
        put(new Observer<Number>() {
            @Override
            public void onUpdate(Number value) {
                view.setText(value.toString());
            }
        }, observable);
    }

    public void bind(final TextView view, final ObservableBoolean observable){
        put(new Observer<Boolean>() {
            @Override
            public void onUpdate(Boolean value) {
                view.setText(value.toString());
            }
        }, observable);
    }

    public <T> void bind(final TextView view, final Observable<T> observable){
        put(new Observer<T>() {
            @Override
            public void onUpdate(T value) {
                view.setText(value.toString());
            }
        }, observable);
    }

    public <T> void bind(final Observable<T> observable, Observer<T> observer){
        put(observer, observable);
    }
}