package com.sample.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.sample.validator.PasswordLength;
import com.sample.validator.PasswordMatches;

@PasswordMatches
public class UserFormDto {

    public static interface SignUp {
    }

    public static interface Edit {
    }

    @NotEmpty
    @Length(max = 50)
    private String name;

    @NotEmpty
    @Length(max = 255)
    @Email
    private String email;

    @Length(min = 6, groups = {SignUp.class})
    @PasswordLength(groups = {Edit.class})
    private String password;

    private String passwordConfirmation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }


}
