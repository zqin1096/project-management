package com.jrp.pma.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// Create an annotation. This annotation can only be applied to field.
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface UniqueValue {
	String message() default "Unique constraint violated";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
/*
 * With the @Constraint annotation, we defined the class that is going to
 * validate our field, the message() is the error message that is showed in the
 * user interface and the additional code is most boilerplate code to conforms
 * to the Spring standards.
 */