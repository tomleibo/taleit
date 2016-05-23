package gurstudio.com.taleitapp.adapters.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by gur on 5/23/2016.
 */
public class RecyclerViewHolder<T extends View> extends RecyclerView.ViewHolder {
    final T concreteItemView;
    public RecyclerViewHolder(T itemView) {
        super(itemView);

        concreteItemView = itemView;
    }

    public T getItemView(){ return concreteItemView; }
}
