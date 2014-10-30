package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.exception.ContactNotFoundException;
import com.noveogroup.tulupov.addressbook.model.Contact;
import com.noveogroup.tulupov.addressbook.model.PageItem;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import com.noveogroup.tulupov.addressbook.util.IPUtils;
import com.noveogroup.tulupov.addressbook.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for MainController class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
@Slf4j
public class MainControllerTest {
    private static final String PATH_DELETE_FORMATTER = "/delete/%d";
    private static final String VIEW_CONTACT_NOT_FOUND = "error_contact_not_found";

    private static final int PAGE_COUNT = 2;
    private static final int COUNT = PageItem.PAGE_SIZE * PAGE_COUNT;

    private static final List<Contact> CONTACTS = new ArrayList<Contact>() {
        {
            for (int i = 0; i < COUNT; i++) {
                final Contact contact = new Contact();
                contact.setFirstname("Vasya");
                contact.setLastname("Pupkin #" + i);
                contact.setEmail("vasya.pupkin." + i + "@gmail.com");
                contact.setPhone(String.format("%07d", i));
                contact.setIp(IPUtils.addressToInteger(String.format("127.0.0.%d", i)));
                add(contact);
            }
        }
    };


    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ContactService contactServiceMock;

    @Before
    public void setUp() {
        Mockito.reset(contactServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


        when(contactServiceMock.query(any(Pageable.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(final InvocationOnMock invocationOnMock) {
                final Pageable pageable = (Pageable) invocationOnMock.getArguments()[0];
                final List<Contact> contacts;

                if (pageable.getOffset() < CONTACTS.size()) {
                    contacts = CONTACTS.subList(pageable.getOffset(),
                            Math.min(pageable.getOffset() + pageable.getPageSize(), CONTACTS.size()));
                } else {
                    contacts = new ArrayList<Contact>();
                }

                return new PageImpl<Contact>(contacts, pageable, COUNT);
            }
        });
    }

    @Test
    public void testListContacts() throws Exception {
        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(view().name("contacts"))
                .andExpect(model().attribute("contactList", hasSize(Config.PAGE_SIZE)))
                .andExpect(model().attribute("pages", hasSize(PAGE_COUNT + 2)));
    }

    @Test
    public void testDeleteContacts() throws Exception {
        final Contact contact = TestUtils.newTestContact();

        mockMvc.perform(get(String.format(PATH_DELETE_FORMATTER, contact.getId())))
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/contacts"));

        verify(contactServiceMock, times(1)).remove(contact.getId());
    }



    @Test
    public void testShowInvalidContact() throws Exception {
        final Contact contact = TestUtils.newTestContact();

        when(contactServiceMock.get(contact.getId())).thenThrow(new ContactNotFoundException());

        mockMvc.perform(get("/contacts/" + contact.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_CONTACT_NOT_FOUND));
    }

    @Test
    public void testDeleteInvalidContact() throws Exception {
        final Contact contact = TestUtils.newTestContact();

        doThrow(new ContactNotFoundException()).when(contactServiceMock).remove(contact.getId());

        mockMvc.perform(get(String.format(PATH_DELETE_FORMATTER, contact.getId())))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_CONTACT_NOT_FOUND));
    }
}
