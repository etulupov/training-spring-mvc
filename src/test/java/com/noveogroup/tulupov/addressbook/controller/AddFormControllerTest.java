package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.model.Contact;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import com.noveogroup.tulupov.addressbook.util.IPUtils;
import com.noveogroup.tulupov.addressbook.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Tests for FormController class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
@Slf4j
public class AddFormControllerTest {
    private static final String PARAM_FIRST_NAME = "firstname";
    private static final String PARAM_LAST_NAME = "lastname";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_IP = "ip";
    private static final String VIEW_CONTACT = "contact";
    private static final String PATH_ADD = "/add";
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ContactService contactServiceMock;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testAddContactInvalidForm() throws Exception {
        final Contact contact = TestUtils.newTestContact();

        contact.setEmail("bademail");

        mockMvc.perform(post(PATH_ADD)
                .param(PARAM_FIRST_NAME, contact.getFirstname())
                .param(PARAM_LAST_NAME, contact.getLastname())
                .param(PARAM_EMAIL, contact.getEmail())
                .param(PARAM_PHONE, contact.getPhone())
                .param(PARAM_IP, IPUtils.addressToString(contact.getIp())))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_CONTACT));

        verify(contactServiceMock, times(0)).add(org.mockito.Matchers.any(Contact.class));
    }


    @Test
    public void testAddContact() throws Exception {
        final Contact contact = TestUtils.newTestContact();

        mockMvc.perform(post(PATH_ADD)
                .param(PARAM_FIRST_NAME, contact.getFirstname())
                .param(PARAM_LAST_NAME, contact.getLastname())
                .param(PARAM_EMAIL, contact.getEmail())
                .param(PARAM_PHONE, contact.getPhone())
                .param(PARAM_IP, IPUtils.addressToString(contact.getIp())))
                .andExpect(status().isMovedTemporarily());

        verify(contactServiceMock, times(1)).add(org.mockito.Matchers.<Contact>argThat(allOf(
                org.hamcrest.Matchers.<Contact>hasProperty(PARAM_FIRST_NAME, equalTo(contact.getFirstname())),
                org.hamcrest.Matchers.<Contact>hasProperty(PARAM_LAST_NAME, equalTo(contact.getLastname())),
                org.hamcrest.Matchers.<Contact>hasProperty(PARAM_EMAIL, equalTo(contact.getEmail())),
                org.hamcrest.Matchers.<Contact>hasProperty(PARAM_PHONE, equalTo(contact.getPhone())),
                org.hamcrest.Matchers.<Contact>hasProperty(PARAM_IP, equalTo(contact.getIp()))
        )));
    }

    @Test
    public void testAddForm() throws Exception {
        mockMvc.perform(get(PATH_ADD))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_CONTACT));
    }
}
