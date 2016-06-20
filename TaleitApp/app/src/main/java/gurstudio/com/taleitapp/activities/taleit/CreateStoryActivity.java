package gurstudio.com.taleitapp.activities.taleit;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.Profile;

import java.util.Collection;
import java.util.List;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.adapters.taleit.CategoryNamesAdapter;
import gurstudio.com.taleitapp.model.core.Observer;
import gurstudio.com.taleitapp.model.taleit.Category;
import gurstudio.com.taleitapp.model.taleit.Story;
import gurstudio.com.taleitapp.network.taleit.CreateStoryRequest;
import gurstudio.com.taleitapp.networkhandlers.taleit.CreateStoryResponseHandler;
import gurstudio.com.taleitapp.verification.core.NotEmpty;
import gurstudio.com.taleitapp.verification.core.VerificationException;
import gurstudio.com.taleitapp.verification.core.ViewsVerifier;

public class CreateStoryActivity extends TaleItActivity {
    @NotEmpty
    private EditText storyName;
    @NotEmpty
    private TextView author;
    @NotEmpty
    private EditText rootTitle;
    @NotEmpty
    private EditText rootContent;
    @NotEmpty
    private TextView categoriesLabel;

    private Category selectedCategory;
    private RecyclerView categoriesRecycler;
    private ImageView apply;

    @Override
    protected int getContentViewLayoutResourceId() {
        return R.layout.activity_create_story;
    }

    @Override
    protected void findViews() {
        storyName = (EditText)findViewById(R.id.story_name);
        author = (TextView) findViewById(R.id.story_author);

        categoriesLabel = (TextView)findViewById(R.id.categories_label);
        categoriesRecycler = (RecyclerView)findViewById(R.id.categories_recycler);

        rootTitle = (EditText)findViewById(R.id.root_title);
        rootContent = (EditText)findViewById(R.id.root_content);

        apply = (ImageView)findViewById(R.id.apply);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    protected void initViews() {
        initApply();

        initCategories();

        initCategoryLabel();

        initAuthor();
    }

    private void initAuthor() {
        Profile profile = getBaseApplication().getApplicationModel().getFacebookProfile().get();
        if (profile != null) {
            author.setText(profile.getName());
        }

        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FacebookLoginActivity.class);
            }
        });

        getCreateBinder().bind(getBaseApplication().getApplicationModel().getFacebookProfile(), new Observer<Profile>() {
            @Override
            public void onUpdate(Profile value) {
                if (value != null) {
                    author.setText(value.getName());
                }
            }
        });
    }

    private void initCategoryLabel() {
        categoriesLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoriesRecycler.getVisibility() == View.VISIBLE) {
                    categoriesRecycler.setVisibility(View.GONE);
                } else {
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
                AccessToken userToken = getBaseApplication().getApplicationModel().getAccessToken();
                if (userToken == null) {
                    startActivity(FacebookLoginActivity.class);
                    return;
                }

                Collection<VerificationException> errors = ViewsVerifier.verifyActivityViews(CreateStoryActivity.this);

                if (!errors.isEmpty()){
                    Dialog dialog = new Dialog(CreateStoryActivity.this);
                    dialog.setTitle(errors.iterator().next().getMessage());
                    dialog.show();
                    return;
                }

                final Story newStory = new Story();
                newStory.title.set(storyName.getText().toString());
                newStory.author.set(getBaseApplication().getApplicationModel().getFacebookProfile().get().getName());
                newStory.category.set(selectedCategory.name.get());
                newStory.root.get().title.set(rootTitle.getText().toString());
                newStory.root.get().text.set(rootContent.getText().toString());

                getBaseApplication().getNetworkManager().sendAsync(CreateStoryRequest
                        .create(
                                storyName.getText().toString(),
                                selectedCategory.name.get(),
                                rootTitle.getText().toString(),
                                rootContent.getText().toString(),
                                getBaseApplication().getApplicationModel().getUserCookie().get(),
                                new CreateStoryResponseHandler(getBaseApplication(), newStory)
                        ));
            }
        });
    }
}