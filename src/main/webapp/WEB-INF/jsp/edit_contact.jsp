<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="label.title"/></title>
    <script type="text/javascript" src="<c:url value="/resources/js/validator.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/validator_strings.json?callback=init"/>"></script>
</head>
<body>

<div class="page-header"><h3><spring:message code="label.editcontact"/></h3></div>

<div class="col-sm-3">
    <img src="../photo/${contact.id}" alt="" class="photo-big"/>

    <div class="row voffset">
        <div class="col-md-1 col-md-offset-4"><a href="../photo/${contact.id}/delete" class="btn btn-default"><spring:message
                code="label.delete"/></a></div>
    </div>
</div>

<div class="col-sm-9">
    <form:form class="form-horizontal" role="form" id="add-form" method="post" action="" commandName="contact"
               enctype="multipart/form-data">
        <fieldset>

            <jsp:include page="include_form.jsp"/>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="submit" class="btn btn-default"><spring:message code="label.save"/></button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>

</body>
</html>





