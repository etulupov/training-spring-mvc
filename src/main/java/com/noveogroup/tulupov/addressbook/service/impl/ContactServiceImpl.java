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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Contact service implementation.
 */
@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ContactServiceImpl extends AbstractServiceImpl<Integer, Contact> implements ContactService {

    private ContactDao contactDao;

    @Autowired
    public ContactServiceImpl(final ContactDao contactDao) {
        super(contactDao);
        this.contactDao = contactDao;
    }

    @Override
    public Page<Contact> query(final Pageable pageable) {
        return new PageImpl<Contact>(contactDao.query(pageable), pageable, contactDao.count());
    }

    @Override
    public void update(final Contact contact) {
        final Integer id = contact.getId();
        if (id == null) {
            throw new IllegalStateException("Contact id is null");
        }

        final Contact savedContact = get(id);
        if (savedContact == null) {
            throw new ContactNotFoundException("Cannot find contact with given id=" + id);
        }

        savedContact.setFirstname(contact.getFirstname());
        savedContact.setLastname(contact.getLastname());
        savedContact.setEmail(contact.getEmail());
        savedContact.setPhone(contact.getPhone());
        savedContact.setIp(contact.getIp());

        final byte[] photo = contact.getPhoto();
        if (photo != null) {
            savedContact.setPhoto(photo);
        }
    }

    @Override
    public long count() {
        return contactDao.count();
    }

    @Override
    public void removePhoto(final Integer id) {
        final Contact contact = get(id);

        if (contact == null) {
            throw new ContactNotFoundException("Cannot remove photo with given id=" + id);
        }

        contact.setPhoto(null);
        contactDao.update(contact);
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
