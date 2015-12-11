<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>

    <jsp:attribute name="page_heading">
        Vehicles
    </jsp:attribute>

    <jsp:attribute name="page_body">

        <form  class="form-inline" style="text-align:center;" role="form" action="list" method="get">

            <div class="form-group">
                <label for="input-vehicle-group" class="col-sm-5 control-label">Vehicle Group</label>
                <div class="col-sm-7">
                    <select class="form-control" id="input-vehicle-group" name="vehicle_group_id">
                        <option value="0">All</option>
                        <c:forEach items="${vehicleGroups}" var="grp" varStatus="status">

                            <option value="${grp.id}"
                                <c:if test="${selectedVehicleGroup == grp.id}">
                                    selected="true"
                                </c:if>
                            >${grp.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2">
                    <button type="submit" class="btn btn-default btn-raised">Search</button>
                </div>
            </div>

        </form>

        <hr/>

        <table class="table table-striped table-hover ">
            <thead>
                <tr>
                    <th>Vehicle ID</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Auth. Code</th>
                    <th>Details</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${vehicleList}" var="v" varStatus="status">
                    <tr>
                        <td>${v.vehicleId}</td>
                        <td>${v.name}</td>
                        <td>${v.vehicleStatus}</td>
                        <td>${v.authenticationCode}</td>
                        <td>${v.details}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </jsp:attribute>

</t:wrapper>