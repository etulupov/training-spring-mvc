<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="title" required="true" %>

<c:choose>
    <c:when test="${sort.getOrderFor(name) == null}">
        <c:set var="newOrder" value="asc"/>
        <c:set var="indicatorClass" value="${null}"/>
    </c:when>
    <c:when test="${sort.getOrderFor(name).isAscending()}">
        <c:set var="newOrder" value="desc"/>
        <c:set var="indicatorClass" value="dropup"/>
    </c:when>
    <c:otherwise>
        <c:set var="newOrder" value="asc"/>
        <c:set var="indicatorClass" value="dropdown"/>
    </c:otherwise>
</c:choose>

<c:set var="sortUrl" value="sort=${name},${newOrder}"/>

<th href="?page=${page}&${sortUrl}">
    <div class="th-inner sortable">
        <spring:message code="${title}"/>
        <c:if test="${indicatorClass != null}">
            <span class="order ${indicatorClass}"><span class="caret" style="margin: 10px 5px;"></span></span>
        </c:if>
    </div>
</th>