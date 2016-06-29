package gurstudio.com.taleitapp.activities.taleit;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gurkashi.fj.lambdas.Predicate;
import com.gurkashi.fj.lambdas.Selector;
import com.gurkashi.fj.queries.stracture.Queriable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.adapters.taleit.ParagraphViewAdapter;
import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
import gurstudio.com.taleitapp.model.taleit.Category;
import gurstudio.com.taleitapp.model.taleit.Paragraph;
import gurstudio.com.taleitapp.model.taleit.Story;

public class StoryViewerActivity extends TaleItActivity {
    List<Paragraph> currentChildren = new ArrayList<>();
    TextView storyTitle;
    TextView storyAuthor;
    ImageView image;
    TextView paragraphText;
    TextView paragraphTitle;
    TextView paragraphAuthor;
    RecyclerView paragraphRecycler;
    View continueStory;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewLayoutResourceId() {
        return R.layout.activity_view_story;
    }

    @Override
    public void onBackPressed(){
        if (getBaseApplication().getApplicationModel().getNavigationPath().isEmpty()) {
            super.onBackPressed();
        }
        else {
            Paragraph previousParagraph = getBaseApplication().getApplicationModel().getNavigationPath().pop();
            getBaseApplication().getApplicationModel().setCurrentViewedParagraph(previousParagraph);
            refreshUI();
        }
    }

    @Override
    protected void findViews() {
        storyTitle = (TextView)findViewById(R.id.story_title);
        storyAuthor = (TextView)findViewById(R.id.story_author);
        image = (ImageView) findViewById(R.id.image);
        paragraphText = (TextView)findViewById(R.id.paragraph_text);
        paragraphAuthor = (TextView)findViewById(R.id.paragraph_author);
        paragraphTitle = (TextView)findViewById(R.id.paragraph_title);
        paragraphRecycler = (RecyclerView) findViewById(R.id.paragraph_recycler);
        continueStory = findViewById(R.id.continue_story);
    }

    @Override
    protected void initViews() {
        initContinueStory();
        initParagraphRecyclerView();
        refreshUI();
    }

    @Override
    public void onResume(){
        super.onResume();

        refreshUI();
    }

    private void initContinueStory() { continueStory.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {startActivity(ContinueStoryActivity.class);}});}

    public void refreshUI() {
        final Story currentStory = getBaseApplication().getApplicationModel().getCurrentViewedStory().get();

        storyTitle.setText(currentStory.title.get());
        storyAuthor.setText(currentStory.name.get());

        try {
            String url = Queriable.create(Category.class)
                    .where(new Predicate<Category>() {
                        @Override
                        public boolean predict(Category category) {
                            return category.name.get().equals(currentStory.category.get());
                        }
                    })
                    .select(new Selector<Category, String>() {
                        @Override
                        public String select(Category category) {
                            return category.image.get();
                        }
                    })
                    .single()
                    .execute(((TaleItApplication) image.getContext().getApplicationContext()).getApplicationModel().getCategories());

            Picasso.with(StoryViewerActivity.this)
                    .load(url)
                    .into(image);
        }
        catch (Exception ex){
            Picasso.with(this)
                    .load(R.drawable.logo)
                    .fit()
                    .into(image);
        }

        Paragraph currentParagraph = getBaseApplication().getApplicationModel().getCurrentViewedParagraph().get();
        paragraphText.setText(currentParagraph.text.get());
        paragraphAuthor.setText(currentParagraph.name.get());
        paragraphTitle.setText(currentParagraph.title.get());

        currentChildren.clear();
        for(Paragraph child: currentParagraph.children){
            currentChildren.add(child);
        }

        paragraphRecycler.getAdapter().notifyDataSetChanged();
    }

    public void initParagraphRecyclerView() {
        paragraphRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ParagraphViewAdapter adapter = new ParagraphViewAdapter(currentChildren);

        paragraphRecycler.setAdapter(adapter);
    }
}