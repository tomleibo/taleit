package usecases.Stories;

import lang.SafeObject;
import model.Model;
import model.Paragraph;
import model.Story;
import usecases.ActionUseCase;

/**
 * Created by Shai on 16/01/2016.
 */
public class ContinueStory extends ActionUseCase {
    Paragraph father;
    String text;
    String username;
    Story story;
    Paragraph paragraph;

    public ContinueStory(SafeObject<Model> context, Paragraph father, String text, String username, Story story) {
        super(context);

        this.father = father;
        this.text = text;
        this.username = username;
        this.story = story;
        this.paragraph = null;
    }

    public void perform(Model model) {
        paragraph = model.concactinateParagraph(story, father, text, username);
    }

    public Paragraph getParagraph() {
        return paragraph;
    }
}
