package gurstudio.com.taleitapp.networkhandlers.taleit;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import gurstudio.com.taleitapp.BuildConfig;
import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
import gurstudio.com.taleitapp.model.core.ApplicationModel;
import gurstudio.com.taleitapp.model.taleit.Paragraph;
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

                            buildParagraphTree(root, story.root.get());

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

    private void buildParagraphTree(JSONObject jsonTree, Paragraph node) throws JSONException {
        node.id.set(jsonTree.getString("id"));
        node.text.set(jsonTree.getString("text"));
        node.title.set(jsonTree.getString("title"));
        node.author.set(jsonTree.getString("author"));

        JSONArray children = jsonTree.getJSONArray("children");
        for (int i = 0; i < children.length(); i++){
            Paragraph paragraph = new Paragraph();
            node.children.add(paragraph);
            buildParagraphTree(children.getJSONObject(i), paragraph);
        }
    }
}