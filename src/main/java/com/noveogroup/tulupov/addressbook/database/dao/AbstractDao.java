package com.noveogroup.tulupov.addressbook.database.dao;

import java.io.Serializable;

/**
 * Abstract dao interface.
 *
 * @param <K> the key
 * @param <E> the entity
 */
public interface AbstractDao<K extends Serializable, E> {
    void add(E entity);

    void update(E entity);

    void remove(E entity);

    E get(K key);
}
