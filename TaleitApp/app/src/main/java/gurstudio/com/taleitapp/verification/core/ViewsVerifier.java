package gurstudio.com.taleitapp.verification.core;

import android.app.Activity;
import android.view.View;

import com.gurkashi.fj.lambdas.Predicate;
import com.gurkashi.fj.lambdas.Selector;
import com.gurkashi.fj.queries.stracture.Queriable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by gur on 6/20/2016.
 */
public class ViewsVerifier {
    public static Collection<VerificationException> verifyActivityViews(final Activity activity){
        Field[] fields = activity.getClass().getDeclaredFields();

        Collection<Field> fieldCollection = new ArrayList<>();
        for (Field field: fields){
            fieldCollection.add(field);
        }

        Collection<VerificationException> errors = Queriable.create(Field.class)
                .where(new Predicate<Field>() {
                    @Override
                    public boolean predict(Field field) {
                        return View.class.isAssignableFrom(field.getType());
                    }
                })
                .where(new Predicate<Field>() {
                    @Override
                    public boolean predict(Field field) {
                        return field.getAnnotations().length > 0;
                    }
                })
                .select(new Selector<Field, VerificationException>() {
                    @Override
                    public VerificationException select(Field field) {
                        try {
                            verifyAllFieldAnnotations(field, activity);
                            return null;
                        } catch (VerificationException e) {
                            return e;
                        }
                    }
                })
                .where(new Predicate<VerificationException>() {
                    @Override
                    public boolean predict(VerificationException e) {
                        return e != null;
                    }
                })
                .execute(fieldCollection);

        return errors;
    }

    private static void verifyAllFieldAnnotations(Field field, Activity activity) throws VerificationException {
        for (Annotation annotation: field.getAnnotations()){
            VerificationAnnotationStrategy verificationAnnotationStrategy = createVerificationStrategyForAnnotation(field, annotation, activity);

            if (verificationAnnotationStrategy != null){
                verificationAnnotationStrategy.verify();
            }
        }
    }

    private static VerificationAnnotationStrategy createVerificationStrategyForAnnotation(Field field, Annotation annotation, Activity activity){
        if (NotEmpty.class.isAssignableFrom(annotation.getClass())){
            return new NotEmptyVerificationAnnotationStrategy(field, (NotEmpty)annotation, activity);
        }
        if (Matching.class.isAssignableFrom(annotation.getClass())){
            return new MatchingVerificationAnnotationStrategy(field, (Matching)annotation, activity);
        }
        if (NotMatching.class.isAssignableFrom(annotation.getClass())){
            return new NotMatchingVerificationAnnotationStrategy(field, (NotMatching)annotation, activity);
        }

        return null;
    }
}
