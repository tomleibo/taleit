package gurstudio.com.taleitapp.adapters.taleit;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.adapters.core.RecyclerViewAdapterBase;
import gurstudio.com.taleitapp.model.taleit.Category;

/**
 * Created by gur on 5/23/2016.
 */
public class CategoriesAdapter extends RecyclerViewAdapterBase<CardView, Category> {

    public CategoriesAdapter(List items) {
        super(items);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.view_category;
    }

    @Override
    protected void onBindViewToItem(CardView view, Category item) {
        ((TextView)view.findViewById(R.id.name)).setText(item.name.get());
        ((ImageView) view.findViewById(R.id.image)).setImageURI(Uri.parse(item.image.get()));
    }
}

