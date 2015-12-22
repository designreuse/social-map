<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>

    <jsp:attribute name="page_heading">
        Map
    </jsp:attribute>

    <jsp:attribute name="page_body">
        <form  class="form-inline" style="text-align:center;" role="form" action="map" method="get">

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
                                    >${grp.name}</option>center a form

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

        <script src="https://maps.googleapis.com/maps/api/js"></script>
        <script>

            function initialize() {
                var mapCanvas = document.getElementById('map');
                var mapOptions = {
                    center: new google.maps.LatLng(7.9982888,80.6546358),
                    zoom: 8,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                var map = new google.maps.Map(mapCanvas, mapOptions);

                var vehicle_image ='<c:url value="/images/bus.png" />';
                <c:forEach items="${vehicleList}" var="v" varStatus="status">
                    <c:if test = "${v.vehicleStatus == 'ACTIVE'}">
                        var marker=new google.maps.Marker({
                            position:new google.maps.LatLng(${v.latitude},${v.longitude}),
                            title:'${v.name}',
                            icon:vehicle_image
                        });
                        // To add the marker to the map, call setMap();
                        marker.setMap(map);

                        marker.info = new google.maps.InfoWindow({maxWidth: 200});

                        google.maps.event.addListener(marker, 'click', function() {
                            marker.info.setContent('<b>${v.name}<br>${v.vehicleGroup.start.name} - ${v.vehicleGroup.end.name}<br></b><p align="justify">${v.details}</p>');
                            marker.info.open(map, this);
                        });
                </c:if>

                </c:forEach>

            };

            google.maps.event.addDomListener(window, 'load', initialize);


        </script>

        <div class="text-center" id="map" style="height: 600px;"></div>


    </jsp:attribute>

</t:wrapper>

