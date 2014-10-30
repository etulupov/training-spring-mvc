package com.noveogroup.tulupov.addressbook.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Pagination utils.
 */
public final class PaginationUtils {
    private PaginationUtils() {

    }

    public static Pageable checkRange(final Pageable pageable, final long count) {
        if (pageable.getOffset() >= count) {
            final int totalPages = (int) ((count + pageable.getPageSize() - 1) / pageable.getPageSize());
            final int lastPage = Math.max(totalPages - 1, 0);

            return new PageRequest(lastPage, pageable.getPageSize(), pageable.getSort());
        }

        return pageable;
    }
}
