package com.noveogroup.tulupov.addressbook.model;


import lombok.Getter;
import lombok.Setter;

/**
 * Page item model.
 */
@SuppressWarnings("unused")
public class PageItem {
    @Getter
    @Setter
    private boolean active;

    @Getter
    @Setter
    private boolean disabled;

    @Getter
    @Setter
    private long number;

    @Getter
    @Setter
    private String title;

}
