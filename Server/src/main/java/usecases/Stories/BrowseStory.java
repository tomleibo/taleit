package usecases.Stories;

import exceptions.BrowseStoryException;
import lang.SafeObject;
import model.Model;
import model.Story;
import usecases.ActionUseCase;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Kerzman on 15/01/2016.
 */
public class BrowseStory extends ActionUseCase {
    private Collection<Story> stories;

    public BrowseStory(SafeObject<Model> context) {
        super(context);
    }

    public void perform(Model model) {
        stories = model.getStories();
    }

    public Collection<Story> getStories() {
        if (stories.isEmpty()) {
            throw new BrowseStoryException("no available stories ");
        }
        return stories;
    }


}
