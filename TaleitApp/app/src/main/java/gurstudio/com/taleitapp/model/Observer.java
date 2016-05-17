package gurstudio.com.taleitapp.model;

public interface Observer<T>{
    void onUpdate(T value);
}
