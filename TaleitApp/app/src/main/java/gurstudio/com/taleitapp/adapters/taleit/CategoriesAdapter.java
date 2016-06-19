package gurstudio.com.taleitapp.adapters.taleit;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.activities.taleit.CategoryBrowserActivity;
import gurstudio.com.taleitapp.adapters.core.RecyclerViewAdapterBase;
import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
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
    protected void onBindViewToItem(final CategoryView view, final Category item) {
        ((TextView)view.findViewById(R.id.name)).setText(item.name.get());

        Picasso.with(view.getContext())
                .load(item.image.get())
                .into(((ImageView) view.findViewById(R.id.image)));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TaleItApplication)(view.getContext().getApplicationContext())).getApplicationModel().setCurrentViewedCategory(item);
                startActivityForView(v, CategoryBrowserActivity.class);
            }
        });
    }
}