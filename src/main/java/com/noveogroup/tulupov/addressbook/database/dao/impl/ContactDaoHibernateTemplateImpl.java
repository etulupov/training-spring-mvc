package com.noveogroup.tulupov.addressbook.database.dao.impl;


import com.noveogroup.tulupov.addressbook.database.dao.ContactDao;
import com.noveogroup.tulupov.addressbook.model.Contact;
import org.hibernate.criterion.DetachedCriteria;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contact dao Hibernate template implementation.
 */
public class ContactDaoHibernateTemplateImpl extends AbstractDaoHibernateTemplateImpl<Integer, Contact> implements ContactDao {

    public ContactDaoHibernateTemplateImpl() {
        super(Contact.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Contact> query(final Pageable pageable) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Contact.class);

        Sort sort = pageable.getSort();

        if (sort != null) {
            for (Sort.Order order : sort) {
                criteria.addOrder(order.isAscending() ? Order.asc(order.getProperty()) :
                        Order.desc(order.getProperty()));
            }
        }

        return hibernateTemplate.findByCriteria(criteria,
                pageable.getOffset(), pageable.getPageSize());
    }

    @Override
    public long count() {
        return DataAccessUtils.longResult(hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Contact.class)
                .setProjection(Projections.rowCount())));
    }
}
