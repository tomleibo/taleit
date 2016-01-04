package usecases.Stories;

import lang.SafeObject;
import model.Model;
import model.Story;
import usecases.ActionUseCase;

/**
 * Created by Shai on 23/12/2015.
 */
public class CreateStory extends ActionUseCase {
    String author;
    String title;
    String text;

    static final int MAX_TITLE_LENGTH = 3999;
    //http://www.answers.com/Q/What_is_the_longest_book_title


    protected CreateStory(SafeObject<Model> context, String author, String title, String text) {
        super(context);

        this.author = author;
        this.title = title;
        this.text = text;
        // add sub paragraphs later
    }

    protected void pre(){
        // TODO probably not here but, check sqlinjection, xss
        validateAuthor(author);// todo change to cookie
        validateTitle(title);
        validateText(text);

    }

    public void perform(Model model) {
        model.addStory(new Story(author, title, text));
    }

    private void validateAuthor(String author) {
//        if (author == null){
//            throw new AuthorException("Author can't be null");
//        }
    }

    /***
     * no max size for now
     * @param text
     * @return
     */
    private boolean validateText(String text) {
//        if (text == null){
//            throw new AuthorException("text can't be null");
//        }
        return false;
    }

    private void validateTitle(String title) {
//        if(title == null || title.length() == 0){
//            throw new StoryTitleException("Title can't be null or zero length" + MAX_TITLE_LENGTH);
//        }
//        if (title.length() > MAX_TITLE_LENGTH){
//            throw new StoryTitleException("Title is too long, must be less than" + MAX_TITLE_LENGTH);
//        }
    }

    private boolean validateUsername(String username) {
        return false;
    }


}
