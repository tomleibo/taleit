package gurstudio.com.taleitapp.model.core;

public interface Observer<T>{
    void onUpdate(T value);
}
