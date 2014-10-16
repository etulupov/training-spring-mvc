package com.noveogroup.tulupov.addressbook.database;

import com.noveogroup.tulupov.addressbook.model.Contact;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import com.noveogroup.tulupov.addressbook.util.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Database initializer.
 */
@Component
@Slf4j
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private static final int COUNT = 50;

    @Autowired
    private ContactService contactService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        init();
    }

    private void init() {
        if (contactService.count() > 0) {
            log.debug("Database is not empty");
            return;
        }

        log.debug("Start to filling database");

        for (int i = 0; i < COUNT; i++) {
            final Contact contact = new Contact();

            contact.setFirstname("Vasya");
            contact.setLastname("Pupkin #" + i);
            contact.setEmail("vasya.pupkin." + i + "@gmail.com");
            contact.setPhone(String.format("%07d", i));
            contact.setIp(IPUtils.addressToInteger(String.format("127.0.0.%d", i)));

            contactService.add(contact);

            log.debug("Add new contact " + contact);
        }

        log.debug("Completed");
    }
}
