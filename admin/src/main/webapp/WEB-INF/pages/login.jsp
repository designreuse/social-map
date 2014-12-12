<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper_basic>

    <jsp:attribute name="css_file_includes">
        <link href="<c:url value="/css/login.css" />" rel="stylesheet">
    </jsp:attribute>

    <jsp:attribute name="page_body">

        <div class="container">

            <form class="form-signin well" role="form" action="<c:url value='/j_spring_security_check' />" method='POST'>
                    <h2 class="form-signin-heading">Welcome</h2>

                    <label for="input-username" class="sr-only">Username</label>
                    <input type="text" id="input-username" name="username" class="form-control" placeholder="Username" required autofocus>

                    <label for="input-password" class="sr-only">Password</label>
                    <input type="password" id="input-password" name="password" class="form-control" placeholder="Password" required>

                    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                        <label class="text-danger">
                            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                        </label>
                    </c:if>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>

        </div> <!-- /container -->

    </jsp:attribute>

</t:wrapper_basic>