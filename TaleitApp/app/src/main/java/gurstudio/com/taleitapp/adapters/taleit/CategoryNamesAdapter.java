package gurstudio.com.taleitapp.adapters.taleit;

import android.view.View;

import java.util.List;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.activities.taleit.CreateStoryActivity;
import gurstudio.com.taleitapp.adapters.core.RecyclerViewAdapterBase;
import gurstudio.com.taleitapp.model.taleit.Category;
import gurstudio.com.taleitapp.views.core.CustomTextView;

/**
 * Created by gur on 6/18/2016.
 */
public class CategoryNamesAdapter extends RecyclerViewAdapterBase<CustomTextView, Category> {
    public CategoryNamesAdapter(List items) {
        super(items);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.view_custom_textview;
    }

    @Override
    protected void onBindViewToItem(final CustomTextView view, final Category item) {
        view.setText(item.name.get());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CreateStoryActivity) view.getContext()).setSelectedCategory(item);
            }
        });
    }
}