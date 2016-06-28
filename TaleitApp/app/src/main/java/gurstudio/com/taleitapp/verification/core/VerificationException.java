package gurstudio.com.taleitapp.verification.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by gur on 6/20/2016.
 */

public class VerificationException extends Exception {
    final static String MESSAGE_FORMAT = "%s %s : %s";
    public VerificationException(Field field, Annotation annotation){
        super(String.format(MESSAGE_FORMAT, field.getClass().getSimpleName(), field.getName(), annotation.getClass().getSimpleName()));
    }

    public VerificationException(String message){
        super(message);
    }
}
