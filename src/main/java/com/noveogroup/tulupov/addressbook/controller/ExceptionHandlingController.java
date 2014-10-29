package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.exception.ContactNotFoundException;
import org.hibernate.QueryException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Exception handling controller.
 */
@ControllerAdvice
public class ExceptionHandlingController {
    private static final String VIEW_CONTACT_NOT_FOUND = "error_contact_not_found";
    private static final String VIEW_INVALID_SORT_ORDER = "error_invalid_sort_order";

    @ExceptionHandler(ContactNotFoundException.class)
    public String contactNotFoundError() throws IOException {
        return VIEW_CONTACT_NOT_FOUND;
    }

    @ExceptionHandler(QueryException.class)
    public String invalidSortOrderError() throws IOException {
        return VIEW_INVALID_SORT_ORDER;
    }
}
