package com.noveogroup.tulupov.addressbook.database.dao.impl;


import com.noveogroup.tulupov.addressbook.database.dao.AbstractDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;

/**
 * Abstract dao implementation.
 *
 * @param <K> the key
 * @param <E> the entity
 */
public abstract class AbstractDaoHibernateTemplateImpl<K extends Serializable, E> implements AbstractDao<K, E> {
    protected Class<E> entityClass;
    protected HibernateTemplate hibernateTemplate;

    public AbstractDaoHibernateTemplateImpl(final Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void add(final E entity) {
        hibernateTemplate.save(entity);
    }

    @Override
    public void remove(final K key) {
        final E entity = hibernateTemplate.load(entityClass, key);
        hibernateTemplate.delete(entity);
    }

    @Override
    public E get(final K key) {
        final E entity = hibernateTemplate.get(entityClass, key);

        if (entity != null) {
            hibernateTemplate.refresh(entity);
        }

        return entity;
    }

    @Override
    public void update(final E entity) {
        hibernateTemplate.merge(entity);
    }
}
