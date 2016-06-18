package gurstudio.com.taleitapp.networkhandlers.taleit;

import org.json.JSONObject;

import gurstudio.com.taleitapp.application.taleit.TaleItApplication;
import gurstudio.com.taleitapp.model.taleit.Story;

/**
 * Created by gur on 6/18/2016.
 */
public class CreateStoryResponseHandler extends TaleItResponseHandlerBase{
    final Story storyToAdd;

    public CreateStoryResponseHandler(TaleItApplication application, Story newStory) {
        super(application);

        storyToAdd = newStory;
    }

    @Override
    public void onResponse(JSONObject response) {
        getApplication().getApplicationModel().getStories().add(storyToAdd);

        getApplication().getCurrentActivity().finish();
    }
}
