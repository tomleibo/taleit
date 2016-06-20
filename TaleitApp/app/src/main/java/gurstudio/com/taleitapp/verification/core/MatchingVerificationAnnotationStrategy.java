package gurstudio.com.taleitapp.verification.core;

import android.app.Activity;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MatchingVerificationAnnotationStrategy extends VerificationAnnotationStrategy<Matching>{
    public MatchingVerificationAnnotationStrategy(Field field, Matching annotation, Activity activity){
        super(field, annotation, activity);
    }

    @Override
    protected void verifyFieldValue(Object value) throws VerificationException {
        String text = ((TextView)value).getText().toString();

        for (String regex: getAnnotation().regex()){
            if (text.matches(regex)) {
                return;
            }
        }

        fail(getAnnotation().message());
    }

    @Override
    public Class getFieldTypeToVerify() {
        return TextView.class;
    }
}
