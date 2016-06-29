package taleit.lang;

public class SafeEvent<T,S> extends Event<T,S>{
    @Override
    public synchronized void add(EventHandler<T, S> handler) {
        super.add(handler);
    }

    @Override
    public synchronized void remove(EventHandler<T, S> handler) {
        super.remove(handler);
    }

    @Override
    public synchronized void launch(T arg, S sender) {
        super.launch(arg, sender);
    }
}