package com.noveogroup.tulupov.addressbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Http errors controller.
 */
@Controller
public class HttpErrorController {
    private static final String VIEW_PAGE_NOT_FOUND = "error_404";
    private static final String REDIRECT_PAGE_NOT_FOUND = "redirect:/error/404.html";

    @RequestMapping("/error/404")
    public String contactNotFoundError() {
        return REDIRECT_PAGE_NOT_FOUND;
    }

    @RequestMapping("/error/404.html")
    public String sss() {
        return VIEW_PAGE_NOT_FOUND;
    }
}
