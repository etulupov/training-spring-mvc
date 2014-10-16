<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><decorator:title default="{title}"/></title>

    <link rel="shortcut icon" href="<c:url value="/resources/favicon.ico"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrapValidator.min.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrapValidator.min.js"/>"></script>


    <decorator:head/>
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/"/>"><spring:message code="title"/></a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/add"/>"><spring:message code="menu.add"/></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="menu.language"/><b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="?language=en"><img src="<c:url value="/resources/image/en.png"/>" alt=""/><spring:message
                                code="menu.language.english"/>
                        </a></li>
                        <li><a href="?language=ru"><img src="<c:url value="/resources/image/ru.png"/>" alt=""/><spring:message
                                code="menu.language.russian"/>
                        </a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="container main">
    <decorator:body/>
</div>
</body>
</html>


