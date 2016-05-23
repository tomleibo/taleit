package gurstudio.com.taleitapp.adapters.core;

import android.support.v7.widget.RecyclerView;

import gurstudio.com.taleitapp.views.core.ViewBase;

/**
 * Created by gur on 5/23/2016.
 */
public class RecyclerViewHolder<T extends ViewBase> extends RecyclerView.ViewHolder {
    public RecyclerViewHolder(T itemView) {
        super(itemView);
    }
}
