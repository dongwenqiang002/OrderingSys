package indi.dwq.orderingSys.auth;


import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
    boolean id() default false;
    String  name() default "董文强";
}
