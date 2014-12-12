<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>

    <jsp:attribute name="page_heading">
        Vehicle Authentication Code
    </jsp:attribute>

    <jsp:attribute name="page_body">

        <div class="text-center">

            <h4>Authentication Code</h4>
            <h2> <span class="label label-info">${authenticationCode}</span> </h2>
            <br/>
            <h4>Group ID</h4>
            <h3> <span class="label label-info">${vehicleGroupId}</span> </h3>

        </div>

    </jsp:attribute>

</t:wrapper>