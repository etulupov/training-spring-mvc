package com.noveogroup.tulupov.addressbook.service.impl;


import com.noveogroup.tulupov.addressbook.database.dao.AbstractDao;
import com.noveogroup.tulupov.addressbook.service.AbstractService;

import java.io.Serializable;

/**
 * Abstract service implementation.
 */
public abstract class AbstractServiceImpl<K extends Serializable, E> implements AbstractService<K, E> {

    private AbstractDao<K, E> dao;

    protected AbstractServiceImpl(AbstractDao<K, E> dao) {
        this.dao = dao;
    }

    @Override
    public void add(final E entity) {
        dao.add(entity);
    }

    @Override
    public void remove(final K key) {
        dao.remove(dao.get(key));
    }

    @Override
    public E get(final K key) {
        return dao.get(key);
    }

    @Override
    public void update(final E entity) {
        dao.update(entity);
    }
}
