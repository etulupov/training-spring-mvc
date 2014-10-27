package com.noveogroup.tulupov.addressbook.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Length validator.
 */
public class LengthValidator implements ConstraintValidator<Length, MultipartFile> {

    private long size;

    @Override
    public void initialize(final Length constraintAnnotation) {
        size = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(final MultipartFile file, final ConstraintValidatorContext constraintContext) {
        return file == null || file.getSize() <= size;
    }
}
