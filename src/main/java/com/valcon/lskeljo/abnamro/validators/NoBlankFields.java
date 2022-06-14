package com.valcon.lskeljo.abnamro.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoBlankFieldsValidator.class)
public @interface NoBlankFields {

	String message() default "Collection cannot be null or contain blank fields";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
