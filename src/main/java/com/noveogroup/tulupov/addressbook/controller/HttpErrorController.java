package com.noveogroup.tulupov.addressbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Http errors controller.
 */
@Controller
public class HttpErrorController {
    private static final String VIEW_PAGE_NOT_FOUND = "error_404";
    private static final String REDIRECT_PAGE_NOT_FOUND = "redirect:/error/404.html";

    @RequestMapping(value = "/error/404", method = { RequestMethod.GET, RequestMethod.POST })
    public String contactNotFoundError() {
        return REDIRECT_PAGE_NOT_FOUND;
    }

    @RequestMapping(value = "/error/404.html", method = RequestMethod.GET)
    public String sss() {
        return VIEW_PAGE_NOT_FOUND;
    }
}
