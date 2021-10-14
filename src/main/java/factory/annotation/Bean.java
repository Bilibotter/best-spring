package factory.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Bean {
    String value() default "";
    String intiMethod() default "";
    String destroyMethod() default "";
}
