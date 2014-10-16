package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.model.Contact;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import com.noveogroup.tulupov.addressbook.util.PageWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

import static com.noveogroup.tulupov.addressbook.util.Config.PAGE_SIZE;

/**
 * Main controller.
 */
@Slf4j
@Controller
public class MainController {
    private static final String VIEW_LIST = "contacts";
    private static final String VIEW_SHOW_CONTACT = "show_contact";
    private static final String REDIRECT_VIEW_LIST = "redirect:/contacts";
    private static final String MODEL_CONTACT = "contact";

    @Autowired
    private ContactService contactService;

    @RequestMapping({ "/", "/contacts" })
    public String listContacts(final Model model,
                               @PageableDefault(size = PAGE_SIZE)
                               final Pageable pageable) {
        final Page<Contact> page = contactService.query(pageable);
        final PageWrapper wrapper = new PageWrapper(page);
        model.addAttribute("contactList", page.getContent());
        model.addAttribute("pages", wrapper.getItems());

        return VIEW_LIST;
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
    public String showContact(@PathVariable final int id,
                              final Model model) throws IOException {
        model.addAttribute(MODEL_CONTACT, contactService.get(id));

        return VIEW_SHOW_CONTACT;
    }

    @RequestMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable("contactId") final Integer contactId) {
        contactService.remove(contactId);

        return REDIRECT_VIEW_LIST;
    }
}
