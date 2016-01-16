package usecases.stories;

import lang.SafeObject;
import model.Model;
import model.Story;
import usecases.ActionUseCase;

import java.util.Map;

/**
 * Created by Kerzman on 15/01/2016.
 */
public class BrowseStories extends ActionUseCase {
    private Map<String, Story> stories;

    public BrowseStories(SafeObject<Model> context) {
        super(context);
    }

    public void perform(Model model) {
        stories = model.getStories();
    }

    public Map<String, Story> getStories() {
        return stories;
    }
}
