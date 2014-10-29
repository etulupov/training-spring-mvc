package com.noveogroup.tulupov.addressbook.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

/**
 * Page item model.
 */
@SuppressWarnings("unused")
@Builder
public class PageItem {
    public static final int PAGE_SIZE = 10;
    
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
