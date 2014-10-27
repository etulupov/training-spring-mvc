package com.noveogroup.tulupov.addressbook.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Length validator annotation.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = LengthValidator.class)
@Documented
@SuppressWarnings("unused")
public @interface Length {

    String message() default "Invalid length";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    long value() default 0;
}
