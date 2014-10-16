package com.noveogroup.tulupov.addressbook.util;

import java.util.regex.Pattern;

/**
 * IP address utils.
 */
public final class IPUtils {
    private static final int BIT_SHIFT_8 = 8;
    private static final int BIT_SHIFT_16 = 16;
    private static final int BIT_SHIFT_24 = 24;
    private static final int MASK = 0xFF;

    private IPUtils() {
        throw new UnsupportedOperationException();
    }

    public static int addressToInteger(final String address) {
        int result = 0;

        for (final String part : address.split(Pattern.quote("."))) {
            result = result << BIT_SHIFT_8;
            result |= Integer.parseInt(part);
        }

        return result;
    }

    public static String addressToString(final int address) {
        return String.format("%s.%s.%s.%s",
                (address >> BIT_SHIFT_24) & MASK,
                (address >> BIT_SHIFT_16) & MASK,
                (address >> BIT_SHIFT_8) & MASK,
                address & MASK);

    }
}
