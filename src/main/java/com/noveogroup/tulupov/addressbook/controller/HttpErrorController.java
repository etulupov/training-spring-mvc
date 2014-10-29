package com.noveogroup.tulupov.addressbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Http errors controller.
 */
@Controller
public class HttpErrorController {
    private static final String VIEW_PAGE_NOT_FOUND = "error_404";

    @RequestMapping("/error/404")
    public String contactNotFoundError() {
        return VIEW_PAGE_NOT_FOUND;
    }
}
