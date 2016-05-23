package gurstudio.com.taleitapp.adapters.taleit;

import android.widget.TextView;

import java.util.List;

import gurstudio.com.taleitapp.adapters.core.RecyclerViewAdapterBase;
import gurstudio.com.taleitapp.model.taleit.Category;

public class TextAdapter extends RecyclerViewAdapterBase<TextView, Category> {

    public TextAdapter(List items) {
        super(items);
    }

    @Override
    public int getViewLayoutId() {
        return TextView.generateViewId();
    }

    @Override
    protected void onBindViewToItem(TextView view, Category item) {
        view.setText(item.name.get());
    }
}
