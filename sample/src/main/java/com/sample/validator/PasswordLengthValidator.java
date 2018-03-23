package com.sample.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class PasswordLengthValidator implements ConstraintValidator<PasswordLength, Object> {

    @Override
    public void initialize(PasswordLength constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String password = (String) value;

        if(StringUtils.isEmpty(password)) {
                return true;
        }

        if(password.length() < 6) {
            return false;
        }

        return true;
    }

}
