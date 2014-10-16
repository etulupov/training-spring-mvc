package com.noveogroup.tulupov.addressbook.util;

import java.beans.PropertyEditorSupport;

/**
 * Ip to string or string to ip editor.
 */
public class IpAddressPropertyEditor extends PropertyEditorSupport {

    public String getAsText() {
        final Object value = getValue();

        if (value instanceof Integer) {
            final Integer ip = (Integer) value;
            return IPUtils.addressToString(ip);
        }

        return "";
    }

    @Override
    public void setAsText(final String address) {
        setValue(IPUtils.addressToInteger(address));
    }
}
