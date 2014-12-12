<%@ tag description="Wrapper tag to use in pages after login" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="page_body" fragment="true" required="true" %>
<%@ attribute name="css_file_includes" fragment="true" %>

<!doctype html>

<html>

    <head>

        <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/css/ripples.min.css" />" rel="stylesheet">
        <link href="<c:url value="/css/material-wfont.min.css" />" rel="stylesheet">

        <!-- for css file includes -->
        <jsp:invoke fragment="css_file_includes"/>

        <title>Social Map</title>
    </head>

    <body>

        <jsp:invoke fragment="page_body"/>


        <script src="<c:url value="/js/jquery-1.11.1.min.js" />"></script>
        <script src="<c:url value="/js/bootstrap.min.js" />"></script>

        <script src="<c:url value="/js/ripples.min.js" />"></script>
        <script src="<c:url value="/js/material.min.js" />"></script>
        <script>
            $(document).ready(function() {
                $.material.init();
            });
        </script>

    </body>

</html>
