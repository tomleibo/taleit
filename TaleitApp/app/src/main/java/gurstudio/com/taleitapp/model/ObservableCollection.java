package gurstudio.com.taleitapp.model;

import android.support.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;

public class ObservableCollection<S> extends Observable<ObservableCollection.ObservableItemAction<S>> implements Collection<S> {
    Collection collection;
    public <T extends Collection> ObservableCollection(Class<T> collectionType){
        try {
            collection = collectionType.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Object object) {
        try {
            return collection.add(object);
        }
        finally {
            set(new ObservableItemAction<>((S) object, ObservableItemAction.ObservableAction.Add));
        }
    }

    @Override
    public boolean addAll(Collection collection) {
        try {
            return this.collection.addAll(collection);
        }
        finally {
            for (Object s: collection){
                set(new ObservableItemAction<S>((S) s, ObservableItemAction.ObservableAction.Add));
            }
        }
    }

    @Override
    public void clear() {
        Object[] copy = new Object[collection.size()];
        try {
            copy = collection.toArray(copy);
            collection.clear();
        }
        finally {
            for (Object o: copy){
                set(new ObservableItemAction<>((S) o, ObservableItemAction.ObservableAction.Remove));
            }
        }
    }

    @Override
    public boolean contains(Object object) {
        return collection.contains(object);
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @NonNull
    @Override
    public Iterator iterator() {
        return collection.iterator();
    }

    @Override
    public boolean remove(Object object) {
        try {
            return collection.remove(object);
        }
        finally {
            set(new ObservableItemAction<>((S) object, ObservableItemAction.ObservableAction.Remove));
        }
    }

    @Override
    public int size() {
        return collection.size();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @NonNull
    @Override
    public S[] toArray(Object[] array) {
        return (S[]) collection.toArray(array);
    }

    @Override
    public boolean retainAll(Collection collection) {
        return this.collection.retainAll(collection);
    }

    @Override
    public boolean removeAll(Collection collection) {
        return this.collection.removeAll(collection);
    }

    @Override
    public boolean containsAll(Collection collection) {
        return this.collection.containsAll(collection);
    }

    public static class ObservableItemAction<I>{
        public enum ObservableAction { Add, Remove }

        public I item;
        public ObservableAction action;

        public ObservableItemAction(I item, ObservableAction action){
            this.item = item;
            this.action = action;
        }
    }
}