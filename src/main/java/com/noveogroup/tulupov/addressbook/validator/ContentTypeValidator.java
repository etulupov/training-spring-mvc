package com.noveogroup.tulupov.addressbook.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Content type annotation.
 */
public class ContentTypeValidator implements ConstraintValidator<ContentType, MultipartFile> {
    private String[] contentTypes;

    @Override
    public void initialize(final ContentType constraintAnnotation) {
        contentTypes = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(final MultipartFile file, final ConstraintValidatorContext constraintContext) {
        if (file == null) {
            return true;
        }

        if (file.isEmpty()) {
            return true;
        }

        for (final String type : contentTypes) {
            if (type.equals(file.getContentType())) {
                return true;
            }
        }

        return false;
    }
}
