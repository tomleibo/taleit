package lang;

public interface EventHandler<T,S>{
    public void handle(T arg, S sender);
}
