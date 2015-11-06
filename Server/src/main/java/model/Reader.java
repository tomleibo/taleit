package model;

public interface Reader<T,S>{
    public S read(T value);
}
