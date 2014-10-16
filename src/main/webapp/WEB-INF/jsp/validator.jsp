<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tags/functions.tld" prefix="f" %>



$(document).ready(function () {
$('#add-form').bootstrapValidator({
fields: {
firstname: {
validators: {
notEmpty: {
message: '<spring:message code="error.contact.firstname.invalid_size"/>'
}
}
},
lastname: {
validators: {
notEmpty: {
message: '<spring:message code="error.contact.lastname.invalid_size"/>'
}
}
},
email: {
validators: {
notEmpty: {
message: '<spring:message code="error.contact.email.empty"/>'
},
emailAddress: {
message: '<spring:message code="error.contact.email.invalid_email"/>'
}
}
},
phone: {
validators: {
notEmpty: {
message: '<spring:message code="error.contact.phone.empty"/>'
},
regexp: {
regexp: "^[0-9\-\+]{3,15}$",
message: '<spring:message code="error.contact.phone.invalid_format"/>'
}
}
},


ip: {
validators: {
notEmpty: {
message: '<spring:message code="error.contact.ip.empty"/>'
},
regexp: {
regexp: "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",
message: '<spring:message code="error.contact.ip.invalid_format"/>'
}
}
},


file: {
validators: {
file: {
extension: 'jpeg,jpg',
type: 'image/jpeg',
maxSize: 1024 * 1024,
message: '<spring:message code="error.contact.file.invalid"/>'
}
}
}


}
});
});

