package gurstudio.com.taleitapp.adapters.core;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gurstudio.com.taleitapp.views.core.ViewBase;

/**
 * Created by gur on 5/23/2016.
 */
public abstract class RecyclerViewAdapterBase<T extends ViewBase, S> extends RecyclerView.Adapter<RecyclerViewHolder<T>> {
    private final List<S> items;

    public RecyclerViewAdapterBase(List<S> items) {
        this.items = items;
    }

    public abstract int getViewLayoutId();

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(getViewLayoutId(), parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new RecyclerViewHolder<>((T) v);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder<T> holder, int position) {
        onBindViewToItem((T) holder.itemView, items.get(position));
    }

    protected abstract void onBindViewToItem(T view, S item);
}