package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.exception.ContactNotFoundException;
import com.noveogroup.tulupov.addressbook.model.Contact;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import com.noveogroup.tulupov.addressbook.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.noveogroup.tulupov.addressbook.model.PageItem.PAGE_SIZE;

/**
 * Main controller.
 */
@Controller
public class MainController {
    private static final String VIEW_LIST = "contacts";
    private static final String VIEW_SHOW_CONTACT = "show_contact";
    private static final String REDIRECT_VIEW_LIST = "redirect:/contacts";
    private static final String REDIRECT_VIEW_LIST_WITH_PAGINATION = REDIRECT_VIEW_LIST + "/?page=";
    private static final String MODEL_CONTACT = "contact";
    private static final String MODEL_CONTACT_LIST = "contactList";
    private static final String MODEL_PAGES = "pages";
    private static final String MODEL_PAGE = "page";
    private static final String MODEL_SORT = "sort";

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = { "/", "/contacts" }, method = RequestMethod.GET)
    public String listContacts(final Model model,
                               @PageableDefault(size = PAGE_SIZE)
                               final Pageable pageable) {
        final Page<Contact> page = contactService.query(pageable);
        final PageWrapper wrapper = new PageWrapper(page);

        model.addAttribute(MODEL_CONTACT_LIST, page.getContent());
        model.addAttribute(MODEL_PAGES, wrapper.getItems());
        model.addAttribute(MODEL_PAGE, pageable.getPageNumber());
        model.addAttribute(MODEL_SORT, pageable.getSort());

        return VIEW_LIST;
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
    public String showContact(@PathVariable final int id,
                              final Model model) {
        final Contact contact = contactService.get(id);

        if (contact == null) {
            throw new ContactNotFoundException("Cannot load contact by id=" + id);
        }

        model.addAttribute(MODEL_CONTACT, contact);

        return VIEW_SHOW_CONTACT;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable("id") final Integer id,
                                @RequestParam(required = false) final String page) {
        try {
            contactService.remove(id);
        } catch (DataAccessException e) {
            throw new ContactNotFoundException("Cannot delete contact by id=" + id, e);
        }

        if (page != null) {
            return REDIRECT_VIEW_LIST_WITH_PAGINATION + page;
        } else {
            return REDIRECT_VIEW_LIST;
        }
    }
}
