package lang;

public interface Function<T>{
    <S> S perform(T value);
}
