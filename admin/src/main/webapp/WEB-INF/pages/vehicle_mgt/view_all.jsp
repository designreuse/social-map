<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>

    <jsp:attribute name="page_heading">
        Vehicles
    </jsp:attribute>

    <jsp:attribute name="page_body">

         <c:if test="${VehicleDeleted== true}">
             <div class="alert alert-success alert-dismissible" role="alert">
                 <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span
                         class="sr-only">Close</span></button>
                 <strong>Success</strong> Vehicle deleted
             </div>
         </c:if>


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
                    <tr id="vehicleinfo">
                        <td>${v.vehicleId}</td>
                        <td>${v.name}</td>
                        <td>${v.vehicleStatus}</td>
                        <td>${v.authenticationCode}</td>
                        <td>${v.details}</td>
                        <td><a href="#" data-toggle="modal" data-target="#myModal" style="text-decoration: none"
                               data-id="${v.id}"
                               data-vehicle-id="${v.vehicleId}"
                               data-name="${v.name}"
                               data-status="${v.vehicleStatus}"
                               data-authcode="${v.authenticationCode}"
                               data-details="${v.details}"> <span class="glyphicon glyphicon-pencil"></span></a></td>

                        <td>


                                <a href="#" data-toggle="modal" data-id="${v.id}" data-target="#confirmDelete">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true" style="color: #009587"></span>
                                </a>

                        </td>
                    </tr>

                </c:forEach>

            </tbody>
        </table>



        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #009587">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Update Details</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form" action="<c:url value="/vehicle/update"/>" method="post">
                            <input type="hidden" id="id" name="id"/>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="vid">Vehicle ID</label>
                                <div class="col-sm-8">

                                    <input type="text" class="form-control" id="vid" placeholder="" name="vehicleId" readonly/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="name">Name </label>
                                <div class="col-sm-8">

                                    <input type="text" class="form-control" id="name" name="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="status">Status </label>
                                <div class="col-sm-8">

                                    <input type="text" class="form-control" id="status" name="vehicleStatus">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="control-label col-sm-4" for="code">Auth. Code </label>
                                <div class="col-sm-8">

                                    <input type="text" class="form-control" id="code" name="authenticationCode" readonly/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="details">Details:</label>
                                <div class="col-sm-8">

                                    <textarea class="form-control" rows="5" id="details" name="details"></textarea>
                                </div>
                            </div>
    <input type="submit" value="Save" hidden="hidden" id="sv"/>

                        </form>

                    </div>
                    <div class="modal-footer">
                        <input type="button" id="btnSend" class="button btn square" onclick="document.getElementById('sv').click();" style="font-weight: normal" value="Save" />
                        <%--<input type="button" id="btnClear" class="button btn square"  style="font-weight: normal;" value="Clear" />--%>
                    </div>
                </div>

            </div>
        </div>



        <!-- Modal Dialog -->
	<div class="modal fade" id="confirmDelete" role="dialog" aria-labelledby="confirmDeleteLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="background-color: #009587">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Delete Permanently</h4>
                </div>
                <div class="modal-body">
                    <p>Are you sure about this ?</p>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <a href="#" class="btn btn-default" id="confirm">Delete</a>

                </div>
            </div>
        </div>
    </div>
    </jsp:attribute>

</t:wrapper>

<script>

    $('#myModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var vehicleId = button.data('vehicle-id'); // Extract info from data-* attributes
        var name = button.data('name');
        var status = button.data('status');
        var authcode = button.data('authcode');
        var details = button.data('details');
        var id = button.data('id');

        var modal = $(this);
        modal.find('#vid').val(vehicleId); //set values
        modal.find('#name').val(name);
        modal.find('#status').val(status);
        modal.find('#code').val(authcode);
        modal.find('#details').val(details);
        modal.find('#id').val(id);
    });

    $('#confirmDelete').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        $("#confirm").attr("href", '<c:url value="/vehicle/delete/"/>'+ button.data('id'));


    });



</script>