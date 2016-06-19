package gurstudio.com.taleitapp.adapters.taleit;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.activities.taleit.StoryViewerActivity;
import gurstudio.com.taleitapp.adapters.core.RecyclerViewAdapterBase;
import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
import gurstudio.com.taleitapp.model.taleit.Story;
import gurstudio.com.taleitapp.model.taleit.TaleItModel;
import gurstudio.com.taleitapp.views.taleit.StoryCardView;

/**
 * Created by gur on 5/24/2016.
 */
public class StoryCardsAdapter extends RecyclerViewAdapterBase<StoryCardView, Story> {

    public StoryCardsAdapter(List<Story> items) {
        super(items);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.view_story_card;
    }

    @Override
    protected void onBindViewToItem(final StoryCardView view, final Story item) {
        ((TextView)view.findViewById(R.id.story_name)).setText(item.title.get());
        ((TextView)view.findViewById(R.id.story_content)).setText(item.root.get().text.get());
        ((TextView)view.findViewById(R.id.author)).setText(item.author.get());

        try {
            Picasso.with(view.getContext())
                    .load(item.image.get())
                    .into(((ImageView) view.findViewById(R.id.image)));
        }
        catch (Exception ex){
            Picasso.with(view.getContext())
                    .load(R.drawable.logo)
                    .into(((ImageView) view.findViewById(R.id.image)));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaleItModel model = ((TaleItApplication)(view.getContext().getApplicationContext())).getApplicationModel();

                model.setCurrentViewedStory(item);
                model.setCurrentViewedParagraph(item.root.get());
                model.getNavigationPath().clear();
                startActivityForView(view, StoryViewerActivity.class);
            }
        });
    }
}