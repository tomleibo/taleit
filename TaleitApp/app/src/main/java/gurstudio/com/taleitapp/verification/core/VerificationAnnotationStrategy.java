package gurstudio.com.taleitapp.verification.core;

import android.app.Activity;
import android.widget.TextView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by gur on 6/20/2016.
 */
public abstract class VerificationAnnotationStrategy<A extends Annotation> {
    private final Field field;
    private final A annotation;
    private final Activity activity;

    protected VerificationAnnotationStrategy(Field field, A annotation, Activity activity){
        this.field = field;
        this.annotation = annotation;
        this.activity = activity;
    }

    public void verify() throws VerificationException{
        verifyFieldType(field, annotation);

        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            TextView value;
            value = (TextView) field.get(activity);
            field.setAccessible(accessible);

            verifyFieldValue(value);

        } catch (IllegalAccessException e) {
            throw new VerificationException(field, annotation);
        }
    }

    protected abstract void verifyFieldValue(Object value) throws VerificationException;

    protected abstract Class getFieldTypeToVerify();

    private void verifyFieldType(Field field, A annotation) throws VerificationException {
        Class child = field.getType();

        if (!getFieldTypeToVerify().isAssignableFrom(child)){
            throw new VerificationException(field, annotation);
        }
    }

    public VerificationException fail() throws VerificationException {
        throw new VerificationException(field, annotation);
    }

    public VerificationException fail(String message) throws VerificationException {
        throw new VerificationException(message);
    }

    public A getAnnotation() { return annotation; }
}

