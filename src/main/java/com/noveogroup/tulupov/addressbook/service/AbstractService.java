package com.noveogroup.tulupov.addressbook.service;


import java.io.Serializable;

/**
 * Abstract service interface.
 *
 * @param <K> the key
 * @param <E> the entity
 */
public interface AbstractService<K extends Serializable, E> {
    void add(E entity);

    void update(E entity);

    void remove(K key);

    E get(K key);
}
