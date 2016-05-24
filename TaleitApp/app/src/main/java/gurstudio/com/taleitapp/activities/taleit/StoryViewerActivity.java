package gurstudio.com.taleitapp.activities.taleit;

import android.widget.TextView;

import gurstudio.com.taleitapp.R;

public class StoryViewerActivity extends TaleItActivity{
    private TextView title;
    private TextView text;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_view_story;
    }

    @Override
    protected void findViews() {
        title = (TextView)findViewById(R.id.title);
        text = (TextView)findViewById(R.id.text);
    }

    @Override
    protected void initViews() {
        title.setText(getBaseApplication().getApplicationModel().getCurrentViewedStory().root.get().title.get());
        text.setText(getBaseApplication().getApplicationModel().getCurrentViewedStory().root.get().text.get());
    }
}
