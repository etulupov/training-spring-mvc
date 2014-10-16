package com.noveogroup.tulupov.addressbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Resources controller.
 */
@Controller
public class ResourcesController {
    @RequestMapping("/resources/js/validator.js")
    public String test() {
        return "validator";
    }
}
