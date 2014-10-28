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
    @ExceptionHandler(ContactNotFoundException.class)
    public String contactNotFoundError() throws IOException {
        return "error_contact_not_found";
    }

    @ExceptionHandler(QueryException.class)
    public String invalidSortOrderError() throws IOException {
        return "error_invalid_sort_order";
    }
}
