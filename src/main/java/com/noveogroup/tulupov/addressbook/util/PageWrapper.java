package com.noveogroup.tulupov.addressbook.util;


import com.noveogroup.tulupov.addressbook.model.Contact;
import com.noveogroup.tulupov.addressbook.model.PageItem;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Page wrapper.
 */
public final class PageWrapper {
    @Getter
    @SuppressWarnings("all")
    private List<PageItem> items;

    public PageWrapper(final Page<Contact> page) {
        if (page.getTotalElements() != 0) {
            items = new ArrayList<>();

            final int pageCount = page.getTotalPages();
            final int current = page.getNumber();

            PageItem pageItem = new PageItem();
            pageItem.setNumber(Math.max(current - 1, 0));
            pageItem.setTitle("&laquo;");
            pageItem.setActive(false);
            pageItem.setDisabled(current == 0);
            items.add(pageItem);

            for (int i = 0; i < pageCount; i++) {
                pageItem = new PageItem();
                pageItem.setNumber(i);
                pageItem.setTitle(String.valueOf(i + 1));
                pageItem.setActive(i == current);
                items.add(pageItem);
            }

            pageItem = new PageItem();
            pageItem.setNumber(Math.min(current + 1, pageCount - 1));
            pageItem.setTitle("&raquo;");
            pageItem.setActive(false);
            pageItem.setDisabled(current == pageCount - 1);
            items.add(pageItem);
        }
    }
}
