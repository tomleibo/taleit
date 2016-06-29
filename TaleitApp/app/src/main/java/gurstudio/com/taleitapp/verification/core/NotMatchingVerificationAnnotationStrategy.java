package gurstudio.com.taleitapp.verification.core;

import android.app.Activity;
import android.widget.TextView;

import java.lang.reflect.Field;

public class NotMatchingVerificationAnnotationStrategy extends VerificationAnnotationStrategy<NotMatching>{
    public NotMatchingVerificationAnnotationStrategy(Field field, NotMatching annotation, Activity activity){
        super(field, annotation, activity);
    }

    @Override
    protected void verifyFieldValue(Object value) throws VerificationException {
        String text = ((TextView)value).getText().toString();

        for (String regex: getAnnotation().regex()){
            if (text.matches(regex)) {
                fail(getAnnotation().message());
            }
        }
    }

    @Override
    public Class getFieldTypeToVerify() {
        return TextView.class;
    }
}
