package usecases.Stories;

import exceptions.InvalidUseCaseParameterException;
import lang.SafeObject;
import model.Model;
import model.Story;
import usecases.ActionUseCase;

/**
 * Created by Shai on 23/12/2015.
 */
public class CreateStory extends ActionUseCase {
    String title;
    String text;
    String username;
    Story story;

    static final int MAX_TITLE_LENGTH = 3999;
    //http://www.answers.com/Q/What_is_the_longest_book_title


    public CreateStory(SafeObject<Model> context, String title, String text, String username) {
        super(context);

        this.username = username;
        this.title = title;
        this.text = text;
    }

    protected void pre(){
        // TODO probably not here but, check sqlinjection, xss
        validateUsername(username);// todo change to cookie
        validateTitle(title);
        validateText(text);
    }

    public void perform(Model model) {
        model.addStory(story = new Story(username, title, text));
    }

    public Story getStory() {
        return story;
    }

    private void validateUsername(String author) {
        if (author == null){
            throw new InvalidUseCaseParameterException("Author" , "can't be null");
        }
    }

    private void validateTitle(String title) {
        if(title == null || title.length() == 0){
            throw new InvalidUseCaseParameterException("Title", "can't be null or zero length");
        }
        if (title.length() > MAX_TITLE_LENGTH){
            throw new InvalidUseCaseParameterException("Title", "is too long, must be less than" + MAX_TITLE_LENGTH);
        }
    }

    /***
     * no max size for now
     * @param text
     * @return
     */
    private boolean validateText(String text) {
        if (text == null){
            throw new InvalidUseCaseParameterException("Text", "can't be null");
        }
        return false;
    }

}
