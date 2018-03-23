package com.sample.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sample.dto.UserFormDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>{

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserFormDto formDto = (UserFormDto)value;
        return formDto.getPassword().equals(formDto.getPasswordConfirmation());
    }

}
