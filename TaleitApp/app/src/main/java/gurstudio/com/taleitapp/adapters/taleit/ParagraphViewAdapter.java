package gurstudio.com.taleitapp.adapters.taleit;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.activities.taleit.StoryViewerActivity;
import gurstudio.com.taleitapp.adapters.core.RecyclerViewAdapterBase;
import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
import gurstudio.com.taleitapp.model.taleit.Paragraph;
import gurstudio.com.taleitapp.views.taleit.ParagraphView;

/**
 * Created by gur on 6/14/2016.
 */
public class ParagraphViewAdapter extends RecyclerViewAdapterBase<ParagraphView, Paragraph> {

    public ParagraphViewAdapter(List<Paragraph> items) {
        super(items);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.view_paragraph;
    }

    @Override
    protected void onBindViewToItem(final ParagraphView view, final Paragraph item) {
        ((TextView)view.findViewById(R.id.story_name)).setText(item.title.get());
        ((TextView)view.findViewById(R.id.author)).setText(item.author.get());

        view.setParagraph(item);
        view.setBackgroundResource(R.color.theme_green);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoryViewerActivity activity = (StoryViewerActivity)view.getContext();

                Paragraph currentViewedParagraph = TaleItApplication.getTaleItModel().getCurrentViewedParagraph().get();
                TaleItApplication.getTaleItModel().getNavigationPath().push(currentViewedParagraph);

                TaleItApplication.getTaleItModel().setCurrentViewedParagraph(item);

                activity.refreshUI();
            }
        });
    }
}