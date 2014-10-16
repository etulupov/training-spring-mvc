package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.model.Contact;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Contact add form controller.
 */
@Controller
public class AddFormController extends AbstractFormController {
    private static final String CONTACT = "contact";
    private static final String VIEW_CONTACT = CONTACT;
    private static final String MODEL_CONTACT = CONTACT;

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submitForm(
            @ModelAttribute(MODEL_CONTACT)
            @Valid final Contact contact,
            final BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return VIEW_CONTACT;
        }

        final MultipartFile file = contact.getFile();

        if (file != null && !file.isEmpty()) {
            contact.setPhoto(file.getBytes());
        }

        contactService.add(contact);

        return "redirect:/contacts/" + contact.getId();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String form(final Model model) {
        model.addAttribute(MODEL_CONTACT, new Contact());
        return VIEW_CONTACT;
    }
}
