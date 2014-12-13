<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>

    <jsp:attribute name="page_heading">

        Welcome to Social Map

    </jsp:attribute>

    <jsp:attribute name="page_body">

        <img src="<c:url value="/images/map.png" />" />

    </jsp:attribute>

</t:wrapper>