package com.noveogroup.tulupov.addressbook.controller;

import com.noveogroup.tulupov.addressbook.util.IpAddressPropertyEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Abstract form controller.
 */
public abstract class AbstractFormController {

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Integer.class, new IpAddressPropertyEditor());
    }
}

