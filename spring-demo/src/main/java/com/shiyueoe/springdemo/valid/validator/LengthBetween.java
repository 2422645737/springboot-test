package com.shiyueoe.springdemo.valid.validator;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = LengthBetweenValidator.class)
public @interface LengthBetween {

    int min();
    int max();

    String message() default "长度不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}