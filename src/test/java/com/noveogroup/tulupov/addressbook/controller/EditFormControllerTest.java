package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.exception.ContactNotFoundException;
import com.noveogroup.tulupov.addressbook.model.Contact;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import com.noveogroup.tulupov.addressbook.util.IPUtils;
import com.noveogroup.tulupov.addressbook.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for FormController class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
@Slf4j
public class EditFormControllerTest {
    private static final String PARAM_ID = "id";
    private static final String PARAM_FIRST_NAME = "firstname";
    private static final String PARAM_LAST_NAME = "lastname";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_IP = "ip";
    private static final String MODEL_CONTACT = "contact";
    private static final String VIEW_EDIT_CONTACT = "edit_contact";
    private static final String PATH_EDIT = "/edit/";
    private static final String VIEW_CONTACT_NOT_FOUND = "error_contact_not_found";

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ContactService contactServiceMock;

    @Before
    public void setUp() {
        Mockito.reset(contactServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testEditContact() throws Exception {
        final Contact contact = TestUtils.newTestContact();

        when(contactServiceMock.get(contact.getId())).thenReturn(contact);

        mockMvc.perform(get(PATH_EDIT + contact.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_EDIT_CONTACT))
                .andExpect(model().attribute(MODEL_CONTACT, contact));

        verify(contactServiceMock, only()).get(contact.getId());
    }

    @Test
    public void testEditSubmitContact() throws Exception {
        final Contact contact = TestUtils.newTestContact();

        mockMvc.perform(post(PATH_EDIT + contact.getId())
                .param(PARAM_FIRST_NAME, contact.getFirstname())
                .param(PARAM_LAST_NAME, contact.getLastname())
                .param(PARAM_EMAIL, contact.getEmail())
                .param(PARAM_PHONE, contact.getPhone())
                .param(PARAM_IP, IPUtils.addressToString(contact.getIp())))
                .andExpect(status().isMovedTemporarily());

        verify(contactServiceMock, times(1)).update(org.mockito.Matchers.<Contact>argThat(allOf(
                org.hamcrest.Matchers.<Contact>hasProperty(PARAM_ID, equalTo(contact.getId())),
                org.hamcrest.Matchers.<Contact>hasProperty(PARAM_FIRST_NAME, equalTo(contact.getFirstname())),
                org.hamcrest.Matchers.<Contact>hasProperty(PARAM_LAST_NAME, equalTo(contact.getLastname())),
                org.hamcrest.Matchers.<Contact>hasProperty(PARAM_EMAIL, equalTo(contact.getEmail())),
                org.hamcrest.Matchers.<Contact>hasProperty(PARAM_PHONE, equalTo(contact.getPhone())),
                org.hamcrest.Matchers.<Contact>hasProperty(PARAM_IP, equalTo(contact.getIp()))
        )));
    }

    @Test
    public void testEditContactInvalidForm() throws Exception {
        final Contact contact = TestUtils.newInvalidTestContact();

        mockMvc.perform(post(PATH_EDIT + contact.getId())
                .param(PARAM_FIRST_NAME, contact.getFirstname())
                .param(PARAM_LAST_NAME, contact.getLastname())
                .param(PARAM_EMAIL, contact.getEmail())
                .param(PARAM_PHONE, contact.getPhone())
                .param(PARAM_IP, IPUtils.addressToString(contact.getIp())))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_EDIT_CONTACT));

        verify(contactServiceMock, never()).update(org.mockito.Matchers.any(Contact.class));
    }

    @Test
    public void testEditContactNotFound() throws Exception {
        final Contact contact = TestUtils.newTestContact();

        doThrow(new ContactNotFoundException())
                .when(contactServiceMock).update(org.mockito.Matchers.any(Contact.class));

        mockMvc.perform(post(PATH_EDIT + contact.getId())
                .param(PARAM_FIRST_NAME, contact.getFirstname())
                .param(PARAM_LAST_NAME, contact.getLastname())
                .param(PARAM_EMAIL, contact.getEmail())
                .param(PARAM_PHONE, contact.getPhone())
                .param(PARAM_IP, IPUtils.addressToString(contact.getIp())))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_CONTACT_NOT_FOUND));
    }


}
