package usecases.Stories;

import lang.Function;
import lang.SafeObject;
import model.Model;
import model.Story;
import model.User;
import usecases.ActionUseCase;

/**
 * Created by Shai on 16/01/2016.
 */
public class LikeStory extends ActionUseCase {
    User user;
    Story story;

    public LikeStory(SafeObject<Model> context, final String storyId, final String cookie) {
        super(context);

        context.read(new Function<Model>() {
            public Void perform(Model model) {
                LikeStory.this.story = model.getStory(storyId);
                LikeStory.this.user = model.getUserFromCookie(cookie);

                return null; // returning null to satisfy method declaration
            }
        });
    }

    public void perform(Model model) {
        story.addLike(user);
    }
}
