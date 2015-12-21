<%--
  Created by IntelliJ IDEA.
  User: anupama
  Date: 12/8/15
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>


    <jsp:attribute name="page_heading">
        Vehicle Groups
    </jsp:attribute>

   <jsp:attribute name="page_body">

         <c:if test="${CouldNotDeleteVehicleGroup == false}">
             <div class="alert alert-danger alert-dismissible" role="alert">
                 <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span
                         class="sr-only">Close</span></button>
                 <strong>Error!</strong> Could not delete vehicle group
             </div>
         </c:if>

        <c:if test="${CouldNotDeleteVehicleGroup== true}">
            <div class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <strong>Success</strong> Vehicle group deleted
            </div>
        </c:if>



        <hr/>

        <table class="table table-striped table-hover ">
            <thead>
            <tr>
                <th>Group</th>
                <th>Details</th>
                <th>Start</th>
                <th>End</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${vehicleGroups}" var="v" varStatus="status">
                <tr>
                    <td>${v.name}</td>
                    <td>${v.details}</td>
                    <td>${v.start.name}</td>
                    <td>${v.end.name}</td>
                    <td><a href="#" data-toggle="modal" data-target="#myModalgroup" style="text-decoration: none"
                           data-id="${v.id}"
                           data-name="${v.name}"
                           data-details="${v.details}"
                           data-start="${v.start.id}"
                           data-end="${v.end.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>

                    <td>
                        <a href="#" data-toggle="modal" data-id="${v.id}" data-target="#confirmDelete">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true" style="color: #009587"></span>
                        </a>
                    </td>



                </tr>
            </c:forEach>

            </tbody>
        </table>

        <div class="modal fade" id="myModalgroup" role="dialog">
            <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header" style="background-color: #009587">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Update Details</h4>
                        </div>
                        <div class="modal-body">
                                <form  class="form-horizontal" role="form" action="<c:url value="/vehiclegroup/update"/>" method="post">

                            <input type="hidden" id="id" name="id"/>
                                    <input type="hidden" id="id2" name="id"/>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="gname">Group </label>

                                <div class="col-sm-8">

                                    <input type="text" class="form-control" id="gname" placeholder="" name="name"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4" for="details">Details:</label>

                                <div class="col-sm-8">

                                    <textarea class="form-control" rows="5" id="details" name="details"></textarea>
                                </div>
                            </div>


                                    <div class="form-group">
                                        <label class="control-label col-sm-4" for="start">Start</label>
                                        <div class="col-sm-7">
                                            <select class="form-control small" id="start" name="start_location" required="required">
                                                <c:forEach items="${locations}" var="loc" varStatus="status">
                                                    <option value="${loc.id}">${loc.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-4" for="start">End</label>
                                        <div class="col-sm-7">
                                            <select class="form-control small" id="end" name="end_location" required="required">
                                                <c:forEach items="${locations}" var="loc" varStatus="status">
                                                    <option value="${loc.id}">${loc.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                            <input type="submit" value="Save" id="svgroup" hidden="hidden"/>

                                </form>

                        </div>
                        <div class="modal-footer">
                            <input type="button" id="btnSendgroup" class="button btn square"
                                   onclick="document.getElementById('svgroup').click();" style="font-weight: normal"
                                   value="Save"/>
                        </div>
                    </div>

                <%--</form:form>--%>
            </div>
        </div>




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
    <jsp:attribute name="js">
    <script>
        $(document).ready(function () {
            $('#myModalgroup').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget); // Button that triggered the modal
                var name = button.data('name');// Extract info from data-* attributes
                var details = button.data('details');
                var start = button.data('start');
                var end = button.data('end');
                var id = button.data('id');

                var modal = $(this);
                modal.find('#gname').val(name);//set values
                modal.find('#details').val(details);
                modal.find("#start").val(start);
                modal.find("#end").val(end);
                modal.find('#id').val(id);
            });


            $('#confirmDelete').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget); // Button that triggered the modal
                $("#confirm").attr("href", '<c:url value="/vehiclegroup/delete/"/>'+ button.data('id'));

            });

        });


    </script>
</jsp:attribute>
</t:wrapper>


