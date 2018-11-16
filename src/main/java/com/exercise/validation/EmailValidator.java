package com.exercise.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String>{

	@Override
	public void initialize(Email arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		String emailRegex = "[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,6})";
		return value.matches(emailRegex);
	}

}
