package com.noveogroup.tulupov.addressbook.service.impl;


import com.noveogroup.tulupov.addressbook.database.dao.ContactDao;
import com.noveogroup.tulupov.addressbook.exception.ContactNotFoundException;
import com.noveogroup.tulupov.addressbook.model.Contact;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Contact service implementation.
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactDao contactDao;

    @Override
    public void add(final Contact contact) {
        contactDao.add(contact);
    }

    @Override
    public Page<Contact> query(final Pageable pageable) {
        return new PageImpl<Contact>(contactDao.query(pageable), pageable, contactDao.count());
    }

    @Override
    public void remove(final int id) {
        checkContact(id);

        contactDao.remove(id);
    }

    @Override
    public Contact get(final int id) {
        checkContact(id);

        return contactDao.get(id);
    }

    private void checkContact(final int id) {
        if (!contactDao.isExist(id)) {
            throw new ContactNotFoundException();
        }
    }

    @Override
    public void update(final Contact contact) {
        if (!contactDao.isExist(contact.getId())) {
            throw new ContactNotFoundException();
        }
        contactDao.update(contact);
    }

    @Override
    public long count() {
        return contactDao.count();
    }

    @Override
    public void removePhoto(final int id) {
        checkContact(id);

        final Contact contact = get(id);
        contact.setPhoto(null);

        update(contact);
    }

    @Override
    public byte[] getPhoto(final int id) {
        checkContact(id);

        final Contact contact = get(id);
        if (contact != null) {
            return contact.getPhoto();
        }

        return null;
    }
}
