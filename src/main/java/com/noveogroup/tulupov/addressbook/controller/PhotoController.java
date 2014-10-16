package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.service.ContactService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Photo controller.
 */
@Controller
public class PhotoController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
    public void getUserImage(final HttpServletResponse response, @PathVariable("id") final int id) throws IOException {
        final byte[] buffer = contactService.getPhoto(id);

        final InputStream is;

        if (buffer != null) {
            is = new ByteArrayInputStream(buffer);
        } else {
            final Resource resource = applicationContext.getResource("resources/image/no_avatar.jpg");
            is = resource.getInputStream();
        }

        response.setContentType("image/jpeg");
        try {
            IOUtils.copy(is, response.getOutputStream());
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    @RequestMapping(value = "/photo/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") final int id) throws IOException {
        contactService.removePhoto(id);

        return "redirect:/edit/" + id;
    }
}
