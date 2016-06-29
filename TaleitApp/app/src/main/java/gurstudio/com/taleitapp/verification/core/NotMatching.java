package gurstudio.com.taleitapp.verification.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotMatching {
    String[] regex() default { ".*" };
    String message() default "Text is not matching pattern";
}
