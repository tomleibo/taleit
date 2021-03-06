package gurstudio.com.taleitapp.activities.taleit;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gurkashi.fj.lambdas.Predicate;
import com.gurkashi.fj.queries.stracture.Queriable;

import java.util.List;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.adapters.taleit.StoryCardsAdapter;
import gurstudio.com.taleitapp.model.taleit.Story;

/**
 * Created by gur on 5/24/2016.
 */
public class CategoryBrowserActivity extends TaleItActivity {

    private RecyclerView storiesRecycler;

    @Override
    protected int getContentViewLayoutResourceId() {
        return R.layout.activity_category_browser;
    }

    @Override
    protected void findViews() {
        storiesRecycler = (RecyclerView)findViewById(R.id.stories_recycler);
    }

    @Override
    protected void initViews() {
        initCategoriesRecyclerView();
    }

    private void initCategoriesRecyclerView() {
        storiesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<Story> relevantStories = (List<Story>) Queriable.create(Story.class).where(new Predicate<Story>() {
            @Override
            public boolean predict(Story story) {
                String storyCategory = story.category.get();
                String viewedCategory = getBaseApplication().getApplicationModel().getCurrentViewedCategory().get().name.get();
                return storyCategory.equals(viewedCategory);
            }
        }).execute(getBaseApplication().getApplicationModel().getStories());

        StoryCardsAdapter adapter = new StoryCardsAdapter(relevantStories);

        storiesRecycler.setAdapter(adapter);
    }
}

