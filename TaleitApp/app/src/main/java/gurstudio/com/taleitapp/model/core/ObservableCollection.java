package gurstudio.com.taleitapp.model.core;

import android.support.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ObservableCollection<S> extends Observable<ObservableCollection.ObservableItemAction<S>> implements List<S> {
    List list;
    public <T extends List> ObservableCollection(Class<T> collectionType){
        try {
            list = collectionType.getConstructor().newInstance();
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
            return list.add(object);
        }
        finally {
            set(new ObservableItemAction<>((S) object, ObservableItemAction.ObservableAction.Add));
        }
    }

    @Override
    public boolean addAll(Collection collection) {
        try {
            return this.list.addAll(collection);
        }
        finally {
            for (Object s: collection){
                set(new ObservableItemAction<>((S) s, ObservableItemAction.ObservableAction.Add));
            }
        }
    }

    @Override
    public void add(int location, S object) {
        try {
            list.add(location, object);
        }
        finally {
            set(new ObservableItemAction<>(object, ObservableItemAction.ObservableAction.Add));
        }
    }

    @Override
    public boolean addAll(int location, Collection<? extends S> collection) {
        try{
            return list.addAll(location, collection);
        }
        finally {
            for (S item: collection){
                set(new ObservableItemAction<>(item, ObservableItemAction.ObservableAction.Add));
            }
        }
    }

    @Override
    public void clear() {
        Object[] copy = new Object[list.size()];
        try {
            copy = list.toArray(copy);
            list.clear();
        }
        finally {
            for (Object o: copy){
                set(new ObservableItemAction<>((S) o, ObservableItemAction.ObservableAction.Remove));
            }
        }
    }

    @Override
    public boolean contains(Object object) {
        return list.contains(object);
    }

    @Override
    public S get(int location) {
        return (S) list.get(location);
    }

    @Override
    public int indexOf(Object object) {
        return list.indexOf(object);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @NonNull
    @Override
    public Iterator iterator() {
        return list.iterator();
    }

    @Override
    public int lastIndexOf(Object object) {
        return list.lastIndexOf(object);
    }

    @Override
    public ListIterator<S> listIterator() {
        return list.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<S> listIterator(int location) {
        return list.listIterator(location);
    }

    @Override
    public S remove(int location) {
        S value = null;
        try {
            value = (S) list.remove(location);
            return value;
        }
        finally {
            set(new ObservableItemAction<>(value, ObservableItemAction.ObservableAction.Remove));
        }
    }

    @Override
    public boolean remove(Object object) {
        try {
            return list.remove(object);
        }
        finally {
            set(new ObservableItemAction<>((S) object, ObservableItemAction.ObservableAction.Remove));
        }
    }

    @Override
    public S set(int location, S object) {
        S old = (S) list.get(location);
        try {
            return (S) list.set(location, object);
        }
        finally {
            set(new ObservableItemAction<S>(old, ObservableItemAction.ObservableAction.Remove));
            set(new ObservableItemAction<S>(object, ObservableItemAction.ObservableAction.Add));
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @NonNull
    @Override
    public List<S> subList(int start, int end) {
        return list.subList(start, end);
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @NonNull
    @Override
    public S[] toArray(Object[] array) {
        return (S[]) list.toArray(array);
    }

    @Override
    public boolean retainAll(Collection collection) {
        return this.list.retainAll(collection);
    }

    @Override
    public boolean removeAll(Collection collection) {
        try {
            return this.list.removeAll(collection);
        }
        finally {
            for(Object item: collection){
                set(new ObservableItemAction<>((S) item, ObservableItemAction.ObservableAction.Remove));
            }
        }
    }

    @Override
    public boolean containsAll(Collection collection) {
        return this.list.containsAll(collection);
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