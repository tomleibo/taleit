package usecases.Stories;

import exceptions.InvalidUseCaseParameterException;
import lang.Function;
import lang.SafeObject;
import model.Model;
import model.Paragraph;
import model.Story;
import model.User;
import usecases.ActionUseCase;

public class CreateStory extends ActionUseCase {
    String title;
    Paragraph root;
    Story result;

    public static final int MAX_TITLE_LENGTH = 100;

    public CreateStory(SafeObject<Model> context, String title, final String cookie, String rootTitle, String rootText) {
        super(context);

        this.title = title;

        User user = context.read(new Function<Model>() {
            public User perform(Model model) {
                return model.getUserFromCookie(cookie);
            }
        });

        this.root = new Paragraph(null, rootText, rootTitle, user);
    }

    protected void pre(){
        validateTitle(title);
    }

    public void perform(Model model) {
        result = new Story(title, root);
        model.addStory(result);
    }

    private void validateTitle(String title) {
        if(title == null || title.length() == 0){
            throw new InvalidUseCaseParameterException("Title", "can't be null or zero length");
        }
        if (title.length() > MAX_TITLE_LENGTH){
            throw new InvalidUseCaseParameterException("Title", "is too long, must be less than" + MAX_TITLE_LENGTH);
        }
    }

    public Story getStory() {
        return result;
    }

    private void validateUsername(String author) {
        if (author == null){
            throw new InvalidUseCaseParameterException("Author" , "can't be null");
        }
    }
}
