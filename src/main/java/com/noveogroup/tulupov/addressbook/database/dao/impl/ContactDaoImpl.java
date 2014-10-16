package com.noveogroup.tulupov.addressbook.database.dao.impl;


import com.noveogroup.tulupov.addressbook.database.dao.ContactDao;
import com.noveogroup.tulupov.addressbook.model.Contact;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contact dao implementation.
 */
@Repository
public class ContactDaoImpl implements ContactDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(final Contact contact) {
        sessionFactory.getCurrentSession().save(contact);
    }

    @Override
    public List<Contact> query() {
        return sessionFactory.getCurrentSession().createQuery("from Contact")
                .list();
    }

    @Override
    public List<Contact> query(final Pageable pageable) {
        return sessionFactory.getCurrentSession().createCriteria(Contact.class)
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .list();
    }

    @Override
    public void remove(final int id) {
        final Contact contact = (Contact) sessionFactory.getCurrentSession().load(
                Contact.class, id);
        if (null != contact) {
            sessionFactory.getCurrentSession().delete(contact);
        }
    }

    @Override
    public Contact get(final int id) {
        final Contact contact = (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);
        sessionFactory.getCurrentSession().refresh(contact);
        return contact;
    }

    @Override
    public void update(final Contact contact) {
        sessionFactory.getCurrentSession().update(contact);
    }

    @Override
    public long count() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(Contact.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public boolean isExist(final int id) {
        final Contact contact = (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);
        return contact != null;
    }
}
