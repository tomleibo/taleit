package taleit.usecases.Stories;

import taleit.lang.Function;
import taleit.lang.SafeObject;
import taleit.model.Model;
import taleit.model.Paragraph;
import taleit.model.Story;
import taleit.model.User;
import taleit.usecases.ActionUseCase;

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

    public ContinueStory(SafeObject<Model> context, final String storyId, final String paragraphId, final String title, final String text, final String cookie) {
        super(context);

        context.read(new Function<Model>() {
            public Void perform(Model model) {
                ContinueStory.this.story = model.getStory(storyId);
                ContinueStory.this.father = story.getParagraphById(paragraphId);
                ContinueStory.this.user = model.getUserFromCookie(cookie);
                ContinueStory.this.title = title;
                ContinueStory.this. text = text;

                return null; // returning null to satisfy method declaration
            }
        });
    }

    public void perform(Model model) {
        paragraph = model.concactinateParagraph(story, father, title, text, user);
    }

    public Paragraph getParagraph() {
        return paragraph;
    }
}
