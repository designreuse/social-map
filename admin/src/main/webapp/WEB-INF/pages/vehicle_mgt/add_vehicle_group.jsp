<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>

    <jsp:attribute name="page_heading">
        Add Vehicle Group
    </jsp:attribute>

    <jsp:attribute name="page_body">

        <c:if test="${vehicleGroupAddSuccess == false}">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <strong>Error!</strong> Could not save vehicle group. Try again.
            </div>
        </c:if>

        <c:if test="${vehicleGroupAddSuccess == true}">
            <div class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <strong>Success</strong> Vehicle group saved.
            </div>
        </c:if>

        <form:form  class="form-horizontal" commandName='vehicleGroup' role="form" action="add" method="post">

            <div class="form-group">
                <label for="input-name" class="col-sm-2 control-label">Name</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" id="input-name" path="name" placeholder="Name" required="true"/>
                </div>
            </div>
            <div class="form-group">
                <label for="input-details" class="col-sm-2 control-label">Details</label>
                <div class="col-sm-10">
                    <form:textarea class="form-control" id="input-details" path="details" placeholder="Details" rows="3" required="true"></form:textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="input-start" class="col-sm-2 control-label">Start</label>
                <div class="col-sm-10">
                    <form:select class="form-control" id="input-start" path="startLocationId">
                        <c:forEach items="${locations}" var="loc" varStatus="status">
                            <option value="${loc.id}">${loc.name}</option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <label for="input-end" class="col-sm-2 control-label">End</label>
                <div class="col-sm-10">
                    <form:select class="form-control" id="input-end" path="endLocationId">
                        <c:forEach items="${locations}" var="loc" varStatus="status">
                            <option value="${loc.id}">${loc.name}</option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary btn-default">Add</button>
                </div>
            </div>

        </form:form>

    </jsp:attribute>

</t:wrapper>