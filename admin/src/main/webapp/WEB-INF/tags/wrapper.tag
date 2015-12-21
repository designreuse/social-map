<%@ tag description="Wrapper tag to use in pages after login" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tp" tagdir="/WEB-INF/tags/private" %>

<%@ attribute name="page_body" fragment="true" required="true" %>
<%@ attribute name="page_heading" fragment="true" %>
<%@ attribute name="css" fragment="true" %>
<%@ attribute name="js" fragment="true" %>

<!doctype html>

<html>

    <head>

        <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/css/ripples.min.css" />" rel="stylesheet">
        <link href="<c:url value="/css/material-wfont.min.css" />" rel="stylesheet">
        <link href="<c:url value="/css/jquery.dropdown.css" />" rel="stylesheet">
        <link href="<c:url value="/css/sm-layout.css" />" rel="stylesheet">

        <jsp:invoke fragment="css"/>

        <title>Social Map</title>
    </head>

    <body>

        <tp:menu/>

        <div class="body-container container">

             <div  class="well">
                <h2 class="page-heading">
                <jsp:invoke fragment="page_heading"/>
                </h2>
                <hr class="heading-hr">

                <jsp:invoke fragment="page_body"/>
             </div>

        </div>

        <script src="<c:url value="/js/jquery-1.11.1.min.js" />"></script>
        <script src="<c:url value="/js/bootstrap.min.js" />"></script>

        <script src="<c:url value="/js/ripples.min.js" />"></script>
        <script src="<c:url value="/js/material.min.js" />"></script>
        <script>
            $(document).ready(function() {
                $.material.init();
            });
        </script>

        <%--<script src="<c:url value="/js/jquery.dropdown.js" />"></script>--%>
        <script>
          $("select").dropdown();
        </script>

        <jsp:invoke fragment="js"/>

    </body>

</html>
