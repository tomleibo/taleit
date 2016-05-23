package usecases.Stories;

import lang.SafeObject;
import model.Categories;
import model.Model;
import model.Story;
import usecases.ActionUseCase;

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
