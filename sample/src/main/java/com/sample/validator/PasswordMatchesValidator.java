package com.sample.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sample.dto.SignUpFormDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>{

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        SignUpFormDto formDto = (SignUpFormDto)value;
        return formDto.getPassword().equals(formDto.getPasswordConfirmation());
    }

}
