package gurstudio.com.taleitapp.networkhandlers.taleit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import gurstudio.com.taleitapp.BuildConfig;
import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
import gurstudio.com.taleitapp.model.core.ApplicationModel;
import gurstudio.com.taleitapp.model.taleit.Story;
import lambdas.Selector;
import queries.stracture.NestedQuery;

/**
 * Created by gur on 5/18/2016.
 */
public class GetStoriesResponseHandler extends TaleItResponseHandlerBase {
    public GetStoriesResponseHandler(TaleItApplication application) {
        super(application);
    }

    @Override
    public void onResponse(JSONObject response) {
        Collection<JSONObject> data;
        try {
            data = toList(response.getJSONObject("data").getJSONArray("stories"));
        } catch (JSONException e) {
            return;
        }

        try {
            NestedQuery.create(JSONObject.class)
                .select(new Selector<JSONObject, Story>() {
                    @Override
                    public Story select(JSONObject jsonObject) {
                        Story story = new Story();

                        try {
                            JSONObject root = jsonObject.getJSONObject("root");
                            story.category.set(jsonObject.getString("category"));
                            story.id.set(jsonObject.getString("id"));
                            story.author.set(root.getString("author"));
                            story.title.set(jsonObject.getString("title"));
                            story.image.set(jsonObject.getString("image").replace("localhost", BuildConfig.SERVER_ADDRESS));
                            story.root.get().id.set(root.getString("id"));
                            story.root.get().text.set(root.getString("text"));
                            story.root.get().title.set(root.getString("title"));
                            story.root.get().author.set(root.getString("author"));
                        } catch (JSONException ex) {
                        }
                        return story;
                    }
                })
                .select(new Selector<Story, Object>() {
                    @Override
                    public Object select(Story story) {
                        application.getApplicationModel().getStories().add(story);
                        return null;
                    }
                })
                .execute(data);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

