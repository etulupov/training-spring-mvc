package com.noveogroup.tulupov.addressbook.service;


import com.noveogroup.tulupov.addressbook.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Contact service interface.
 */
public interface ContactService extends AbstractService<Integer, Contact> {
    Page<Contact> query(Pageable pageable);

    void removePhoto(Integer id);

    byte[] getPhoto(Integer id);

    long count();
}
