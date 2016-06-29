package taleit.lang;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by gur on 12/9/2015.
 */
public class Event<T,S> {
    final Collection<EventHandler<T, S>> handlers;

    public Event(){
        handlers = new HashSet<EventHandler<T, S>>();
    }

    public void add(EventHandler<T,S> handler){
        handlers.add(handler);
    }

    public void remove(EventHandler<T,S> handler){
        handlers.remove(handler);
    }

    public void launch(T arg, S sender){
        for (EventHandler<T,S> handler: handlers){
            handler.handle(arg, sender);
        }
    }
}

