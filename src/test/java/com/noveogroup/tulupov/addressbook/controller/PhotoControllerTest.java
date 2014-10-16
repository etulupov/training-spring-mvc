package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.exception.ContactNotFoundException;
import com.noveogroup.tulupov.addressbook.model.Contact;
import com.noveogroup.tulupov.addressbook.service.ContactService;
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

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Tests for PhotoControllerTest class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:dispatcher-servlet.xml" })
@WebAppConfiguration
@Slf4j
public class PhotoControllerTest {
    private static final String PATH_FORMATTER = "/photo/%d/delete";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ContactService contactServiceMock;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        Mockito.reset(contactServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testDeletePhoto() throws Exception {
        final Contact contact = TestUtils.newTestContact();

        mockMvc.perform(get(String.format(PATH_FORMATTER, contact.getId())))
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/edit/" + contact.getId()));

        verify(contactServiceMock, times(1)).removePhoto(contact.getId());
    }

    @Test
    public void testDeleteInvalidPhoto() throws Exception {
        final Contact contact = TestUtils.newTestContact();

        doThrow(new ContactNotFoundException()).when(contactServiceMock).removePhoto(contact.getId());

        mockMvc.perform(get(String.format(PATH_FORMATTER, contact.getId())))
                .andExpect(status().isOk())
                .andExpect(view().name("error_contact_not_found"));
    }
}
