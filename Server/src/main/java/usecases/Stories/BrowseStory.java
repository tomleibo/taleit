package usecases.Stories;

import exceptions.InvalidUseCaseParameterException;
import lang.SafeObject;
import model.Model;
import model.Story;
import usecases.ActionUseCase;

import java.util.Map;

/**
 * Created by Kerzman on 15/01/2016.
 */
public class BrowseStory extends ActionUseCase {
    private Map<Integer, Story> stories;

    public BrowseStory(SafeObject<Model> context) {
        super(context);
    }

    public void perform(Model model) {
        stories = model.getStories();
    }

    public Map<Integer, Story> getStories() {
        if (stories.isEmpty()) {
            throw new InvalidUseCaseParameterException("Browse Story", "no available stories ");
        }
        return stories;
    }


}
