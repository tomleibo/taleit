package gurstudio.com.taleitapp.networkhandlers.taleit;

import android.util.Log;

import com.gurkashi.fj.lambdas.Predicate;
import com.gurkashi.fj.lambdas.Selector;
import com.gurkashi.fj.queries.stracture.Queriable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import gurstudio.com.taleitapp.BuildConfig;
import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
import gurstudio.com.taleitapp.model.taleit.Category;
import gurstudio.com.taleitapp.model.taleit.Paragraph;
import gurstudio.com.taleitapp.model.taleit.Story;

/**
 * Created by gur on 5/18/2016.
 */
public class GetStoriesResponseHandler extends TaleItResponseHandlerBase {
    public GetStoriesResponseHandler(TaleItApplication application) {
        super(application);
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.e(getClass().getSimpleName(), response.toString());

        Collection<JSONObject> data;
        try {
            data = toList(response.getJSONObject("data").getJSONArray("stories"));
        } catch (JSONException e) {
            return;
        }

        try {
            getApplication().getApplicationModel().getStories().clear();
            Queriable.create(JSONObject.class)
                .select(new Selector<JSONObject, Story>() {
                    @Override
                    public Story select(JSONObject jsonObject) {
                        Story story = new Story();

                        try {
                            JSONObject root = jsonObject.getJSONObject("root");
                            story.category.set(jsonObject.getString("category"));
                            story.id.set(jsonObject.getString("id"));
                            story.author.set(root.getString("author"));
                            story.name.set(root.getString("name"));
                            story.title.set(jsonObject.getString("title"));
                            story.image.set("http://" + BuildConfig.SERVER_ADDRESS  + ":8080" + jsonObject.getString("image").replace("localhost", BuildConfig.SERVER_ADDRESS));

                            buildParagraphTree(root, story.root.get());

                        } catch (JSONException ex) {
                        }
                        return story;
                    }
                })
                .select(new Selector<Story, Story>() {
                    @Override
                    public Story select(final Story story) {
                        String image = Queriable.create(Category.class)
                                .where(new Predicate<Category>() {
                                    @Override
                                    public boolean predict(Category category) {
                                        return category.name.get().equals(story.category.get());
                                    }
                                })
                                .select(new Selector<Category, String>() {
                                    @Override
                                    public String select(Category category) {
                                        return category.image.get();
                                    }
                                })
                                .single()
                                .execute(getApplication().getApplicationModel().getCategories());
                        story.image.set(image);
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
        node.name.set(jsonTree.getString("author"));

        JSONArray children = jsonTree.getJSONArray("children");
        for (int i = 0; i < children.length(); i++){
            Paragraph paragraph = new Paragraph();
            node.children.add(paragraph);
            buildParagraphTree(children.getJSONObject(i), paragraph);
        }
    }
}