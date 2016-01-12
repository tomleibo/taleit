package exceptions;

/**
 * Created by Shai on 04/01/2016.
 */
public class InvalidUseCaseParameterException extends UsecaseException {
    public InvalidUseCaseParameterException(String param, String message) {
        super("Paramater" + param + ": " + message);
    }
}
