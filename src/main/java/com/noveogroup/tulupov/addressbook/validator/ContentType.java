package com.noveogroup.tulupov.addressbook.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Content type validator annotation.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ContentTypeValidator.class)
@Documented
@SuppressWarnings("unused")
public @interface ContentType {

    String message() default "Invalid content type";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String[] value();

}
