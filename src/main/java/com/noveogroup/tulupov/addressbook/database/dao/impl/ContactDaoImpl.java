package com.noveogroup.tulupov.addressbook.database.dao.impl;


import com.noveogroup.tulupov.addressbook.database.dao.ContactDao;
import com.noveogroup.tulupov.addressbook.model.Contact;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contact dao implementation.
 */
@Repository
public class ContactDaoImpl extends AbstractDaoImpl<Integer, Contact> implements ContactDao {

    public ContactDaoImpl() {
        super(Contact.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Contact> query(final Pageable pageable) {
        return hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Contact.class),
                pageable.getOffset(), pageable.getPageSize());
    }

    @Override
    public long count() {
        return DataAccessUtils.longResult(hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Contact.class)
                .setProjection(Projections.rowCount())));
    }
}
