package usecases.Stories;

import db.DbHandler;
import exceptions.InvalidUseCaseParameterException;
import lang.Function;
import lang.SafeObject;
import model.Categories;
import model.Model;
import model.Paragraph;
import model.Story;
import model.User;
import usecases.ActionUseCase;

public class CreateStory extends ActionUseCase {
    String text;
    String title;
    Paragraph root;
    Categories category;
    Story result;

    public static final int MAX_TITLE_LENGTH = 100;

    public CreateStory(SafeObject<Model> context, String title, final String cookie, String rootTitle, String
            rootText, Categories category) {
        super(context);

        this.title = title;
        this.category = category;
        this.text = rootText;

        User user = context.read(new Function<Model>() {
            public User perform(Model model) {
                return model.getUserFromCookie(cookie);
            }
        });

        this.root = new Paragraph(null, rootText, rootTitle, user);
        DbHandler.getInstance().InsertParagraph(this.root);
    }

    protected void pre(){
        validateTitle(title);
        validateText(text);
    }

    public void perform(Model model) {
        result = new Story(title, root, category);
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

    private void validateText(String text) {
        if(text == null || text.length() == 0){
            throw new InvalidUseCaseParameterException("Text", "can't be null or zero length");
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
