package taleit.usecases.Stories;

import taleit.lang.SafeObject;
import taleit.model.Model;
import taleit.model.Story;
import taleit.usecases.ActionUseCase;

import java.util.Collection;

/**
 * Created by Kerzman on 15/01/2016.
 */
public class BrowseStory extends ActionUseCase {
    private String category;
    private Collection<Story> stories;

    public BrowseStory(SafeObject<Model> context, String category) {
        super(context);
        this.category = category;
    }

    public void perform(Model model) {
        stories = model.getStories(category);
    }

    public Collection<Story> getStories() {
        return stories;
    }
}
