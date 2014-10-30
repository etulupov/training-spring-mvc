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
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-table.css"/>">
</head>
<body>

<div>
    <div class="page-header"><h3><spring:message code="label.contacts"/></h3></div>
<c:choose>
    <c:when test="${!empty contactList}">
        <my:paginator/>

        <table class="table table-striped fixed-table-container">
            <thead>
            <tr>
                <th>&nbsp;</th>
                <my:column name="id" title="label.id"/>
                <my:column name="firstname" title="label.firstname"/>
                <my:column name="lastname" title="label.lastname"/>
                <my:column name="email" title="label.email"/>
                <my:column name="phone" title="label.telephone"/>
                <my:column name="ip" title="label.ip"/>
                <th class="button">&nbsp;</th>
                <th class="button">&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${contactList}" var="contact">
                <tr href="<c:url value="/contacts/${contact.id}"/>" class="table-row">
                    <td><img src="<c:url value="/photo/${contact.id}"/>" alt="" class="photo"/></td>
                    <td>${contact.id}</td>
                    <td>${contact.firstname}</td>
                    <td>${contact.lastname}</td>
                    <td>${contact.email}</td>
                    <td>${contact.phone}</td>
                    <td>${f:IPtoString(contact.ip)}</td>
                    <td class="button"><a href="<c:url value="/edit/${contact.id}"/>"><img
                            src="<c:url value="/resources/image/edit.png"/>" alt=""/> </a>
                    </td>
                    <td class="button"><a href="<c:url value="/delete/${contact.id}?page=${page}"/>"><img
                            src="<c:url value="/resources/image/delete.png"/>" alt=""/>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <my:paginator/>
    </c:when>
    <c:otherwise>
        <div class="alert alert-info"><spring:message code="error.no_contacts"/></div>
    </c:otherwise>
</c:choose>

</div>

</body>
</html>





