package com.noveogroup.tulupov.addressbook.database.dao;

import com.noveogroup.tulupov.addressbook.model.Contact;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Contact dao interface.
 */
public interface ContactDao extends AbstractDao<Integer, Contact> {
    List<Contact> query(Pageable pageable);

    long count();
}
