package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.model.Contact;
import com.noveogroup.tulupov.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Contact edit form controller.
 */
@Controller
public class EditFormController extends AbstractFormController {
    private static final String CONTACT = "contact";
    private static final String VIEW_EDIT_CONTACT = "edit_contact";
    private static final String MODEL_CONTACT = CONTACT;

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editForm(
            @PathVariable final int id,
            @ModelAttribute(MODEL_CONTACT)
            @Valid final Contact contact,
            final BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return VIEW_EDIT_CONTACT;
        }

        contact.setId(id);

        final MultipartFile file = contact.getFile();

        if (file != null && !file.isEmpty()) {
            contact.setPhoto(contact.getFile().getBytes());
        }

        contactService.update(contact);

        return "redirect:/contacts/" + id;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable final int id,
                       final Model model) throws IOException {

        model.addAttribute(MODEL_CONTACT, contactService.get(id));

        return VIEW_EDIT_CONTACT;
    }


}
