package com.noveogroup.tulupov.addressbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Http errors controller.
 */
@Controller
public class HttpErrorController {

    @RequestMapping("/error/404")
    public String contactNotFoundError() {
        return "error_404";
    }
}
