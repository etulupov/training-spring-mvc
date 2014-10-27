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
public class ContactServiceImpl extends AbstractServiceImpl<Integer, Contact> implements ContactService {

    private ContactDao contactDao;

    @Autowired
    public ContactServiceImpl(ContactDao contactDao) {
        super(contactDao);
        this.contactDao = contactDao;
    }

    @Override
    public Page<Contact> query(final Pageable pageable) {
        return new PageImpl<Contact>(contactDao.query(pageable), pageable, contactDao.count());
    }

    @Override
    public long count() {
        return contactDao.count();
    }

    @Override
    public void removePhoto(final Integer id) {
        final Contact contact = get(id);

        if (contact == null) {
            throw new ContactNotFoundException();
        }

        contact.setPhoto(null);
        update(contact);
    }

    @Override
    public byte[] getPhoto(final Integer id) {
        final Contact contact = get(id);

        if (contact != null) {
            return contact.getPhoto();
        }

        return null;
    }
}
