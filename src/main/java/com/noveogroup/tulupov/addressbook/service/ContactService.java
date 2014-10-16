package com.noveogroup.tulupov.addressbook.service;


import com.noveogroup.tulupov.addressbook.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Contact service interface.
 */
public interface ContactService {
    void add(Contact contact);

    void update(Contact contact);

    Page<Contact> query(Pageable pageable);

    void remove(int id);

    void removePhoto(int id);

    byte[] getPhoto(int id);

    long count();

    Contact get(int id);

}
