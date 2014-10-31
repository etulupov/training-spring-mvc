package com.noveogroup.tulupov.addressbook.controller;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Resources controller.
 */
@Controller
public class ResourcesController {
    private static final List<? extends Pair<String, String>> STRINGS =
            Arrays.asList(new ImmutablePair<>("firstname_invalid_size", "error.contact.firstname.invalid_size"),
                    new ImmutablePair<>("lastname_invalid_size", "error.contact.lastname.invalid_size"),
                    new ImmutablePair<>("email_empty", "error.contact.email.empty"),
                    new ImmutablePair<>("email_invalid", "error.contact.email.invalid_email"),
                    new ImmutablePair<>("phone_empty", "error.contact.phone.empty"),
                    new ImmutablePair<>("phone_invalid", "error.contact.phone.invalid_format"),
                    new ImmutablePair<>("ip_empty", "error.contact.ip.empty"),
                    new ImmutablePair<>("ip_invalid", "error.contact.ip.invalid_format"),
                    new ImmutablePair<>("file_invalid", "error.contact.file.invalid"));

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/resources/validator_strings.json", method = RequestMethod.GET)
    public Map<String, String> validatorStrings() {
        final Map<String, String> map = new HashMap<>();
        final Locale locale = LocaleContextHolder.getLocale();

        for (final Pair<String, String> pair : STRINGS) {
            map.put(pair.getLeft(), messageSource.getMessage(pair.getRight(), null, locale));
        }

        return map;
    }
}
