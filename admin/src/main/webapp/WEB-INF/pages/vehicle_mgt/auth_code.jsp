<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>

    <jsp:attribute name="page_heading">
        Vehicle Authentication Code
    </jsp:attribute>

    <jsp:attribute name="page_body">

            <h4>Group ID</h4>

            <h4></h4>
            <h3> <span class="label label-info">${authenticationCode}</span> </h3>

                    <label for="input-auth-code" class="col-sm-2 control-label">Authentication Code</label>

                        <p class="form-control-static" id="input-auth-code"> <span class="label label-info">${vehicleGroupId}</span> </p>


    </jsp:attribute>

</t:wrapper>