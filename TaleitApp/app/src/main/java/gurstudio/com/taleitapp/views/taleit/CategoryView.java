package gurstudio.com.taleitapp.views.taleit;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import gurstudio.com.taleitapp.R;

/**
 * Created by gur on 5/23/2016.
 */
public class CategoryView extends CardView{
    boolean showText;

    public CategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CategoryView, 0, 0);

        try {
            showText = array.getBoolean(R.styleable.CategoryView_show_category_label, true);
        } finally {
            array.recycle();
        }

        // TODO: set the text, find inner stuff etc..
    }
}

