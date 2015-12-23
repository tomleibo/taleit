package lang;

public interface Function<T>{
    public <S> S perform(T value);
}
