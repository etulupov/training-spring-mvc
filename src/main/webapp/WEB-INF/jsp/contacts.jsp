<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tags/functions.tld" prefix="f" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="title"/></title>
</head>
<body>

<div>
    <div class="page-header"><h3><spring:message code="label.contacts"/></h3></div>

    <my:paginator/>

    <c:if test="${!empty contactList}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>&nbsp;</th>
                <th><spring:message code="label.firstname"/></th>
                <th><spring:message code="label.lastname"/></th>
                <th><spring:message code="label.email"/></th>
                <th><spring:message code="label.telephone"/></th>
                <th><spring:message code="label.ip"/></th>
                <th class="button">&nbsp;</th>
                <th class="button">&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${contactList}" var="contact">
                <tr href="<c:url value="/contacts/${contact.id}"/>" class="table-row">
                    <td><img src="<c:url value="/photo/${contact.id}"/>" alt="" class="photo"/></td>
                    <td>${contact.firstname}</td>
                    <td>${contact.lastname}</td>
                    <td>${contact.email}</td>
                    <td>${contact.phone}</td>
                    <td>${f:IPtoString(contact.ip)}</td>
                    <td class="button"><a href="<c:url value="/edit/${contact.id}"/>"><img src="<c:url value="/resources/image/edit.png"/>" alt=""/> </a>
                    </td>
                    <td class="button"><a href="<c:url value="/delete/${contact.id}?page=${page}"/>"><img src="<c:url value="/resources/image/delete.png"/>" alt=""/>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <my:paginator/>
</div>

</body>
</html>





