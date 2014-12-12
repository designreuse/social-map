package hms;/*   File Name - RequestControler
 *
 *   (C) Copyright 2013-2014 hSenid Software International (Pvt) Limited.
 *   All Rights Reserved.
 *
 *   These materials are unpublished, proprietary, confidential source code of
 *   hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
 *   of hSenid Software International (Pvt) Limited.
 *
 *   hSenid Software International (Pvt) Limited retains all title to and intellectual
 *   property rights in these materials.
 *
 */

import hms.model.Vehicle;
import hms.model.VehicleGroup;
import hms.service.UserService;
import hms.service.VehicleGroupService;
import hms.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ishara on 12/7/14.
 */
@Controller
@RequestMapping("/request")
public class RequestController {
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleGroupService vehicleGroupService;
    @Autowired
    private VehicleService vehicleService;

    final static Logger logger = LogManager.getLogger(RequestController.class);

    @RequestMapping(value = "/vehicle/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Map<String, Object> registerVehicle(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            logger.info("Register vehicle: {}", request);
            boolean isVerified = vehicleService.VerifyVehicle(Long.parseLong(request.get("group-id").toString()), request.get("vehicle-id").toString(), request.get("code").toString());

            if(isVerified) {
                response.put("responseContext", ResponseStatus.SUCCESS);
            }
            else {
                response.put("responseContext", ResponseStatus.FAIL);

            }
        } catch (Exception e) {
            response.put("responseContext", ResponseStatus.ERROR);
            logger.error("Error occurred in vehicle verification", e);
        }
        return response;
    }

    @RequestMapping(value = "/vehicle/current/location", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Map<String, Object> currentLocation(@RequestBody Map<String, Object> request) {
        Map response = new HashMap();
        try {
            logger.info("Current vehicle location: {}", request);
            //todo process the request
            response.put("responseContext", "Vehicle current location updated");
        } catch (Exception e) {
            response.put("responseContext", "Internal Error");
        }
        return response;
    }

    @RequestMapping(value = "/user/get/all/groups", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Map<String, Object> searchGroup(@RequestBody Map<String, Object> request) {
        Map response = new HashMap();
        try {
            logger.info("Get all groups: {}", request);
            List<VehicleGroup> vehicleGroups = vehicleGroupService.getAllGroups();
            for (VehicleGroup vehicleGroup : vehicleGroups) {
                vehicleGroup.setVehicles(null);
            }
            //todo process the request
            response.put("responseContext", vehicleGroups);
        } catch (Exception e) {
            response.put("responseContext", "Internal Error");
        }
        return response;
    }

    @RequestMapping(value = "/user/locate/vehicle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Map<String, Object> locateVehicle(@RequestBody Map<String, Object> request) {
        Map response = new HashMap();
        try {
            logger.info("Locate vehicle: {}", request);
            //todo process the request
            Vehicle vehicle = new Vehicle();
            response.put("responseContext", vehicle);
        } catch (Exception e) {
            response.put("responseContext", "Internal Error");
        }
        return response;
    }
}
