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
</head>
<body>


<div class="page-header"><h3><spring:message code="label.show_contact"/></h3></div>

<div class="col-sm-3">
    <img src="../photo/${contact.id}" alt="" class="photo-big"/>
</div>
<div class="col-sm-5">

    </a>

    <ul class="list-group">
        <li class="list-group-item"><b><spring:message code="label.firstname"/></b>: ${contact.firstname}</li>
        <li class="list-group-item"><b><spring:message code="label.lastname"/></b>: ${contact.lastname}</li>
        <li class="list-group-item"><b><spring:message code="label.email"/></b>: ${contact.email}</li>
        <li class="list-group-item"><b><spring:message code="label.telephone"/></b>: ${contact.phone}</li>
        <li class="list-group-item"><b><spring:message code="label.ip"/></b>: ${f:IPtoString(contact.ip)}</li>
        <li class="list-group-item"><b><spring:message code="label.actions"/></b>:
            <a href="../edit/${contact.id}"> <img src="../resources/image/edit.png" alt=""/> </a>
            <a href="../delete/${contact.id}"><img src="../resources/image/delete.png" alt=""/></a>
        </li>
    </ul>
</div>


</body>
</html>





