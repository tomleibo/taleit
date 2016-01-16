package usecases.stories;

import exceptions.InvalidUseCaseParameterException;
import lang.SafeObject;
import model.Model;
import model.Paragraph;
import model.Story;
import usecases.ActionUseCase;

/**
 * Created by Shai on 23/12/2015.
 */
public class CreateStory extends ActionUseCase {
    String title;
    Paragraph root;

    public static final int MAX_TITLE_LENGTH = 100;

    public CreateStory(SafeObject<Model> context, String title, Paragraph root) {
        super(context);

        this.title = title;
        this.root = root;
    }

    protected void pre(){
        validateTitle(title);
    }

    public void perform(Model model) {
        model.addStory(new Story(title, root));
    }

    private void validateTitle(String title) {
        if(title == null || title.length() == 0){
            throw new InvalidUseCaseParameterException("Title", "can't be null or zero length");
        }
        if (title.length() > MAX_TITLE_LENGTH){
            throw new InvalidUseCaseParameterException("Title", "is too long, must be less than" + MAX_TITLE_LENGTH);
        }
    }
}
