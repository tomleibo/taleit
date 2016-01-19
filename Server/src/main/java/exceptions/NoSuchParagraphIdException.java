package exceptions;

/**
 * Created by Shai on 18/01/2016.
 */
public class NoSuchParagraphIdException extends StoryException {
    public NoSuchParagraphIdException(String paragraphId) {
        super("The paragraph you asked for: " + paragraphId + " doesn't exist or has been removed");
    }
}
