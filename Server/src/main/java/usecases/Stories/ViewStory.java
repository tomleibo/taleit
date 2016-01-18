package usecases.Stories;

import lang.SafeObject;
import model.Model;
import model.Paragraph;
import model.Story;
import usecases.ActionUseCase;

/**
 * Created by Shai on 18/01/2016.
 */
public class ViewStory extends ActionUseCase{
    Story story;
    Integer paragraphId;
    Paragraph paragraph;

    public ViewStory(SafeObject<Model> context, Story story, Integer paragraphId) {
        super(context);

        this.story = story;
        this.paragraphId = paragraphId;
    }

    public void perform(Model model) {
        if (paragraphId == null) { // if the user wants the root TODO... not sure if he has it already or not
            paragraph = model.getRootFromStory(story);
        }else{
            paragraph = model.getParagraph(story, paragraphId);
        }
    }

    public Paragraph getParagraph() {
        return paragraph;
    }
}
