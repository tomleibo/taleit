package gurstudio.com.taleitapp.activities.taleit;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;

import java.util.List;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.adapters.taleit.CategoryNamesAdapter;
import gurstudio.com.taleitapp.model.taleit.Category;
import gurstudio.com.taleitapp.model.taleit.Story;
import gurstudio.com.taleitapp.network.taleit.CreateStoryRequest;
import gurstudio.com.taleitapp.networkhandlers.taleit.CreateStoryResponseHandler;

public class CreateStoryActivity extends TaleItActivity {
    private EditText storyName;
    private Category selectedCategory;
    private EditText content;
    private EditText rootTitle;
    private EditText rootContent;
    private TextView categoriesLabel;
    private RecyclerView categoriesRecycler;
    private ImageView apply;


    @Override
    protected int getContentViewLayoutResourceId() {
        return R.layout.activity_create_story;
    }

    @Override
    protected void findViews() {
        storyName = (EditText)findViewById(R.id.story_name);
        content = (EditText)findViewById(R.id.story_description);
        categoriesLabel = (TextView)findViewById(R.id.categories_label);
        categoriesRecycler = (RecyclerView)findViewById(R.id.categories_recycler);

        rootTitle = (EditText)findViewById(R.id.root_title);
        rootContent = (EditText)findViewById(R.id.root_content);

        apply = (ImageView)findViewById(R.id.apply);
    }

    @Override
    protected void initViews() {
        initApply();

        initCategories();

        initCategoryLabel();
    }

    private void initCategoryLabel() {
        categoriesLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoriesRecycler.getVisibility() == View.VISIBLE) {
                    categoriesRecycler.setVisibility(View.GONE);
                }
                else {
                    categoriesRecycler.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void setSelectedCategory(Category category){
        selectedCategory = category;
        categoriesLabel.setText(category.name.get());
    }

    private void initCategories() {
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Category> categories = getBaseApplication().getApplicationModel().getCategories();
        CategoryNamesAdapter adapter = new CategoryNamesAdapter(categories);

        categoriesRecycler.setAdapter(adapter);

        categoriesRecycler.setVisibility(View.GONE);
    }

    private void initApply() {
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId;
                AccessToken userToken = getBaseApplication().getApplicationModel().getUser();
                if (userToken != null){
                    userId = userToken.getUserId();
                }
                else {
                    startActivity(FacebookLoginActivity.class);
                    return;
                }

                final Story newStory = new Story();
                newStory.title.set(storyName.getText().toString());
                newStory.author.set(userId);
                newStory.category.set(selectedCategory.name.get());
                newStory.root.get().title.set(rootTitle.getText().toString());
                newStory.root.get().text.set(rootContent.getText().toString());

                getBaseApplication().getNetworkManager().sendAsync(CreateStoryRequest
                        .create(
                                storyName.getText().toString(),
                                selectedCategory.name.get(),
                                rootTitle.getText().toString(),
                                rootContent.getText().toString(),
                                userId,
                                new CreateStoryResponseHandler(getBaseApplication(), newStory)
                        ));
            }
        });
    }
}