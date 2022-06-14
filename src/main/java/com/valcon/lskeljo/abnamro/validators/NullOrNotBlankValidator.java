package com.valcon.lskeljo.abnamro.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {

	@Override
	public void initialize(NullOrNotBlank constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		return s == null || s.trim().length() > 0;
	}
}
