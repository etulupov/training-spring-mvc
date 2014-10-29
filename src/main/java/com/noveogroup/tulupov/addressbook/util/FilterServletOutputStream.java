package com.noveogroup.tulupov.addressbook.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * Filter servlet output stream.
 */
public class FilterServletOutputStream extends ServletOutputStream {
    private DataOutputStream stream;

    public FilterServletOutputStream(final OutputStream output) {
        stream = new DataOutputStream(output);
    }

    public void write(final int b) throws IOException {
        stream.write(b);
    }

    public void write(final byte[] b) throws IOException {
        stream.write(b);
    }

    public void write(final byte[] b, final int off, final int len) throws IOException {
        stream.write(b, off, len);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(final WriteListener writeListener) {
    }
}
