package com.shiyueoe.springdemo.valid.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LengthBetweenValidator implements ConstraintValidator<LengthBetween,String> {
    private int min;

    private int max;

    @Override
    public void initialize(LengthBetween constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return true;
        }
        int len = s.length();

        return len >= min && len <= max;
    }
}
