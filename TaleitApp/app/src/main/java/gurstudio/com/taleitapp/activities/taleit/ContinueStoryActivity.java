package gurstudio.com.taleitapp.activities.taleit;

import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;

import gurstudio.com.taleitapp.R;

/**
 * Created by gur on 6/14/2016.
 */
public class ContinueStoryActivity extends TaleItActivity {
    private EditText title;
    private EditText content;
    private ImageView apply;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_continue_story;
    }

    @Override
    protected void findViews() {
        title = (EditText)findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);
        apply = (ImageView)findViewById(R.id.apply);
    }

    @Override
    protected void initViews() {
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO:
            }
        });
    }
}
