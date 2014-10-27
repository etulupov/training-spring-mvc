package com.noveogroup.tulupov.addressbook.util;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Stores in session sort parameters.
 */
public class SessionSortHandlerMethodArgumentResolver extends SortHandlerMethodArgumentResolver {
    private static final String SESSION_SORT = "sort";

    @Override
    public Sort resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpSession session = request.getSession();

        Sort sort = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);

        if (sort == null) {
            sort = (Sort) session.getAttribute(SESSION_SORT);
        } else {
            session.setAttribute(SESSION_SORT, sort);
        }

        return sort;
    }
}
