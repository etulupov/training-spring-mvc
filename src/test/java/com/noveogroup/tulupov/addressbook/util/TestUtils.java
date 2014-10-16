package com.noveogroup.tulupov.addressbook.util;

import com.noveogroup.tulupov.addressbook.model.Contact;

/**
 * Test utils.
 */
public final class TestUtils {

    private TestUtils() {
        throw new UnsupportedOperationException();
    }

    public static Contact newTestContact() {
        final Contact contact = new Contact();
        contact.setId(1);
        contact.setFirstname("Vasya");
        contact.setLastname("Pupkin");
        contact.setEmail("vasya.pupkin@gmail.com");
        contact.setPhone("1234567");
        contact.setIp(IPUtils.addressToInteger("127.0.0.1"));
        return contact;
    }

    public static Contact newInvalidTestContact() {
        final Contact contact = newTestContact();
        contact.setEmail("bademail");
        return contact;
    }
}
