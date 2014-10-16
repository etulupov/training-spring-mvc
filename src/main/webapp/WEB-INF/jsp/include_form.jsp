<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


            <div class="form-group">
                <form:label path="firstname" class="col-sm-2 control-label"><spring:message
                        code="label.firstname"/></form:label>
                <div class="col-sm-4">
                    <form:input path="firstname" class="form-control"/>
                    <small class="help-block has-error"><form:errors path="firstname" cssClass="help-block"/></small>
                </div>
            </div>

            <div class="form-group">
                <form:label path="lastname" class="col-sm-2 control-label"><spring:message
                        code="label.lastname"/></form:label>
                <div class="col-sm-4">
                    <form:input path="lastname" class="form-control"/>
                    <small class="help-block has-error"><form:errors path="lastname" cssClass="help-block"/></small>
                </div>
            </div>

            <div class="form-group">
                <form:label path="email" class="col-sm-2 control-label"><spring:message
                        code="label.email"/></form:label>
                <div class="col-sm-4">
                    <form:input path="email" class="form-control"/>
                    <small class="help-block has-error"><form:errors path="email" cssClass="help-block"/></small>
                </div>
            </div>

            <div class="form-group">
                <form:label path="phone" class="col-sm-2 control-label"><spring:message
                        code="label.telephone"/></form:label>
                <div class="col-sm-4">
                    <form:input path="phone" class="form-control"/>
                    <small class="help-block has-error"><form:errors path="phone" cssClass="help-block"/></small>
                </div>
            </div>

            <div class="form-group">
                <form:label path="phone" class="col-sm-2 control-label"><spring:message
                        code="label.ip"/></form:label>
                <div class="col-sm-4">
                    <form:input path="ip" class="form-control"/>
                    <small class="help-block has-error"><form:errors path="ip" cssClass="help-block"/></small>
                </div>
            </div>

            <div class="form-group">
                <form:label path="file" class="col-sm-2 control-label"><spring:message
                        code="label.photo"/></form:label>
                <div class="col-sm-4">
                    <input name="file" type="file" class="form-control"/>
                    <small class="help-block has-error"><form:errors path="file" cssClass="help-block"/></small>
                </div>
            </div>

