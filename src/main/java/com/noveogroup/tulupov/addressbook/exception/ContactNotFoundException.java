package com.noveogroup.tulupov.addressbook.exception;

/**
 * Contact not found exception.
 */
public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException() {
    }

    public ContactNotFoundException(final String message) {
        super(message);
    }

    public ContactNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
