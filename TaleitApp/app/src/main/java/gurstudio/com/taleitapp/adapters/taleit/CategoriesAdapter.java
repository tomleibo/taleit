package gurstudio.com.taleitapp.adapters.taleit;

import android.widget.TextView;

import java.util.List;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.adapters.core.RecyclerViewAdapterBase;
import gurstudio.com.taleitapp.model.taleit.Category;
import gurstudio.com.taleitapp.views.taleit.CategoryView;

/**
 * Created by gur on 5/23/2016.
 */
public class CategoriesAdapter extends RecyclerViewAdapterBase<CategoryView, Category> {

    public CategoriesAdapter(List items) {
        super(items);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.view_category;
    }

    @Override
    protected void onBindViewToItem(CategoryView view, Category item) {
        ((TextView)view.findViewById(R.id.name)).setText(item.name.get());
    }
}