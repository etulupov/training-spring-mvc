package com.noveogroup.tulupov.addressbook.database.dao;

import java.io.Serializable;

/**
 * Abstract dao interface.
 */
public interface AbstractDao<K extends Serializable, E> {
    void add(E entity);

    void update(E entity);

    void remove(E entity);

    E get(K key);
}
