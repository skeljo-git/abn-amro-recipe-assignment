package com.valcon.lskeljo.abnamro.validators;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class NoBlankFieldsValidator implements ConstraintValidator<NoBlankFields, Collection<String>> {

	@Override
	public void initialize(NoBlankFields constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Collection<String> collection, ConstraintValidatorContext constraintValidatorContext) {
		return collection.stream().allMatch(StringUtils::isNotBlank);
	}
}
