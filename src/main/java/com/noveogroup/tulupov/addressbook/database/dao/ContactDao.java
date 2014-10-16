package com.noveogroup.tulupov.addressbook.database.dao;

import com.noveogroup.tulupov.addressbook.model.Contact;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Contact dao interface.
 */
public interface ContactDao {
    void add(Contact contact);

    void update(Contact contact);

    List<Contact> query();

    List<Contact> query(Pageable pageable);

    void remove(int id);

    Contact get(int id);

    boolean isExist(int id);

    long count();
}
