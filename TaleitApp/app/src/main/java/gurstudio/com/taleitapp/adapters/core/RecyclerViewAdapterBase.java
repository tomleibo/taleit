package gurstudio.com.taleitapp.adapters.core;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by gur on 5/23/2016.
 */
public abstract class RecyclerViewAdapterBase<T extends View, S> extends RecyclerView.Adapter<RecyclerViewHolder<T>> {
    private final List<S> items;

    public RecyclerViewAdapterBase(List<S> items) {
        this.items = items;
    }

    public abstract int getViewLayoutId();

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            // create a new view
            T view = (T) LayoutInflater.from(parent.getContext()).inflate(getViewLayoutId(), parent, false);
            // set the view's size, margins, paddings and layout parameters
            return new RecyclerViewHolder<>(view);
        }
        catch (Throwable wtf){
            Log.e(getClass().getSimpleName(), wtf.toString());
            throw wtf;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder<T> holder, int position) {
        onBindViewToItem(holder.getItemView(), items.get(position));
    }

    protected abstract void onBindViewToItem(T view, S item);

    public <A extends Activity> void startActivityForView(View view, Class<A> activityClass){
        view.getContext().startActivity(new Intent(view.getContext(), activityClass));
    }
}