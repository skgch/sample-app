package com.sample.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class SignUpFormDto {

	@NotEmpty
	@Length(max = 50)
	private String username;

	@NotEmpty
	@Length(max = 255)
	@Email
	private String email;

	@NotEmpty
	@Length(min = 6)
	private String password;

	// TODO Password Validation
	@NotEmpty
	private String passwordConfirmation;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
