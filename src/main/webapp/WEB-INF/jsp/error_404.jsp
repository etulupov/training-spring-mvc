<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tags/functions.tld" prefix="f" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="label.title"/></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
</head>
<body>

<div class="container voffset">
    <div class="alert alert-danger"><spring:message code="error"/> <spring:message code="error.404"/></div>
</div>

</body>
</html>





