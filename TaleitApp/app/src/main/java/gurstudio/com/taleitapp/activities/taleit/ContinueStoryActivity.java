package gurstudio.com.taleitapp.activities.taleit;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.model.taleit.Paragraph;
import gurstudio.com.taleitapp.network.taleit.ContinueStoryRequest;
import gurstudio.com.taleitapp.networkhandlers.core.NetworkResponseHandlerBase;
import gurstudio.com.taleitapp.verification.core.NotEmpty;
import gurstudio.com.taleitapp.verification.core.VerificationException;
import gurstudio.com.taleitapp.verification.core.ViewsVerifier;

/**
 * Created by gur on 6/14/2016.
 */
public class ContinueStoryActivity extends TaleItActivity {
    @NotEmpty(message = "Give us your chapter title")
    private EditText title;
    @NotEmpty(message="Give us some content")
    private EditText content;
    private ImageView apply;

    @Override
    protected int getContentViewLayoutResourceId() {
        return R.layout.activity_continue_story;
    }

    @Override
    protected void findViews() {
        title = (EditText)findViewById(R.id.story_name);
        content = (EditText)findViewById(R.id.story_description);
        apply = (ImageView)findViewById(R.id.apply);
    }

    @Override
    protected void initViews() {
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getBaseApplication().getApplicationModel().getUserCookie().get() == null){
                    startActivity(FacebookLoginActivity.class);
                    return;
                }

                Collection<VerificationException> errors = ViewsVerifier.verifyActivityViews(ContinueStoryActivity.this);

                if (!errors.isEmpty()){
                    Dialog dialog = new Dialog(ContinueStoryActivity.this);
                    dialog.setTitle(errors.iterator().next().getMessage());
                    dialog.show();
                    return;
                }

                String cookie = getBaseApplication().getApplicationModel().getUserCookie().get();
                if (cookie == null || cookie.isEmpty() || AccessToken.getCurrentAccessToken() == null || Profile.getCurrentProfile() == null || getBaseApplication().getApplicationModel().getFacebookProfile().get() == null){
                    startActivity(FacebookLoginActivity.class);
                    return;
                }

                ContinueStoryRequest request = ContinueStoryRequest
                        .create(
                                getBaseApplication().getApplicationModel().getCurrentViewedStory().get().id.get(),
                                getBaseApplication().getApplicationModel().getCurrentViewedParagraph().get().id.get(),
                                title.getText().toString(),
                                content.getText().toString(),
                                getBaseApplication().getApplicationModel().getUserCookie().get(),
                                new NetworkResponseHandlerBase(getBaseApplication()) {
                                    @Override
                                    public void onResponse(Object response) {
                                        try {
                                            Paragraph paragraph = new Paragraph();
                                            paragraph.author.set(getBaseApplication().getApplicationModel().getFacebookProfile().get().getName());
                                            paragraph.name.set(getBaseApplication().getApplicationModel().getFacebookProfile().get().getName());
                                            paragraph.text.set(content.getText().toString());
                                            paragraph.title.set(title.getText().toString());
                                            paragraph.id.set(((JSONObject) response).getJSONObject("data").getString("paragraphId"));
                                            getBaseApplication().getApplicationModel().getCurrentViewedParagraph().get().children.add(paragraph);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        finish();
                                    }
                                }
                        );

                getBaseApplication().getNetworkManager().sendAsync(request);
            }
        });
    }
}