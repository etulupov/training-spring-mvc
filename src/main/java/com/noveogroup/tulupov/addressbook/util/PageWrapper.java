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

            items.add(PageItem.builder()
                    .number(Math.max(current - 1, 0))
                    .title("&laquo;")
                    .active(false)
                    .disabled(current == 0)
                    .build());

            for (int i = 0; i < pageCount; i++) {
                items.add(PageItem.builder()
                        .number(i)
                        .title(String.valueOf(i + 1))
                        .active(i == current)
                        .build());
            }

            items.add(PageItem.builder()
                    .number(Math.min(current + 1, pageCount - 1))
                    .title("&raquo;")
                    .active(false)
                    .disabled(current == pageCount - 1)
                    .build());
        }
    }
}
