package usecases.Stories;

import lang.SafeObject;
import model.Model;
import model.Paragraph;
import model.Story;
import model.User;
import usecases.ActionUseCase;

/**
 * Created by Shai on 16/01/2016.
 */
public class ContinueStory extends ActionUseCase {
    Paragraph father;
    String text;
    String title;
    User user;
    Story story;
    Paragraph paragraph;

    public ContinueStory(SafeObject<Model> context, Paragraph father, String title, String text, User user, Story story) {
        super(context);

        this.father = father;
        this.text = text;
        this.user = user;
        this.story = story;
        this.title = title;

        this.paragraph = null; // will be assigned after perform only TODO: change it!!!
    }

    public void perform(Model model) {
        paragraph = model.concactinateParagraph(story, father, title, text, user);
    }

    public Paragraph getParagraph() {
        return paragraph;
    }
}
