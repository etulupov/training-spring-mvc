package com.noveogroup.tulupov.addressbook.filter;

import com.noveogroup.tulupov.addressbook.util.GenericResponseWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Json callback filter.
 */
@WebFilter(filterName = "JsonpCallbackFilter", urlPatterns = "/resources/*")
@Slf4j
public class JsonpCallbackFilter implements Filter {
    private static final String PARAM_CALLBACK = "callback";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ");";
    private static final String CONTENT_TYPE = "text/javascript;charset=UTF-8";

    public void init(final FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        @SuppressWarnings("unchecked")
        final Map<String, String[]> params = httpRequest.getParameterMap();

        if (params.containsKey(PARAM_CALLBACK)) {
            final OutputStream out = httpResponse.getOutputStream();

            final GenericResponseWrapper wrapper = new GenericResponseWrapper(httpResponse);

            chain.doFilter(request, wrapper);

            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write((params.get(PARAM_CALLBACK)[0] + OPEN_BRACKET).getBytes());
            outputStream.write(wrapper.getData());
            outputStream.write(CLOSE_BRACKET.getBytes());
            final byte[] jsonpResponse = outputStream.toByteArray();

            wrapper.setContentType(CONTENT_TYPE);
            wrapper.setContentLength(jsonpResponse.length);

            out.write(jsonpResponse);
            out.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}
