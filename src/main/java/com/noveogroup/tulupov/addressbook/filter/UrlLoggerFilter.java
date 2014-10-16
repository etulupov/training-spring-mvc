package com.noveogroup.tulupov.addressbook.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Shows url address of request.
 */
@Slf4j
@WebFilter(filterName = "UrlLoggerFilter", urlPatterns = "*")
public class UrlLoggerFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws
            ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) req;
        log.info(String.format("URL: %s", request.getRequestURL()));

        chain.doFilter(req, resp);
    }

    public void init(final FilterConfig config) throws ServletException {

    }

}
