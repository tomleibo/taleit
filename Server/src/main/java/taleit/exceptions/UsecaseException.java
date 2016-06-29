package taleit.exceptions;

/**
 * Created by gur on 12/23/2015.
 */
public abstract class UsecaseException extends RuntimeException {
    public UsecaseException(String message) {
        super(message);
    }
}

