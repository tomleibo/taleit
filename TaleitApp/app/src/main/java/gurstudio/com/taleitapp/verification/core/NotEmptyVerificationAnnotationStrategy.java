package gurstudio.com.taleitapp.verification.core;

import android.app.Activity;
import android.widget.TextView;

import java.lang.reflect.Field;

public class NotEmptyVerificationAnnotationStrategy extends VerificationAnnotationStrategy<NotEmpty>{
    public NotEmptyVerificationAnnotationStrategy(Field field, NotEmpty annotation, Activity activity){
        super(field, annotation, activity);
    }

    @Override
    protected void verifyFieldValue(Object value) throws VerificationException {
        if (((TextView)value).getText().toString().isEmpty()){
            fail(getAnnotation().message());
        }
    }

    @Override
    public Class getFieldTypeToVerify() {
        return TextView.class;
    }
}

