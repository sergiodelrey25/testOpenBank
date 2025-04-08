package com.capgemini.test.code.domain.core.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameUppercase.Validator.class)
@Documented
public @interface NameUppercase {
    String message() default "must be uppercase";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	class Validator implements ConstraintValidator<NameUppercase, String> {
		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			return value==null? true:CadenasValidator.isUppercase(value);
		}
	}
}
