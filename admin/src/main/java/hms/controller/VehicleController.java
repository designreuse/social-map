package hms.controller;

import hms.dao.VehicleDao;
import hms.dto.VehicleDto;
import hms.model.Location;
import hms.model.Vehicle;
import hms.model.VehicleGroup;
import hms.service.VehicleGroupService;
import hms.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sadika on 2014-12-12.
 */
@Controller
@RequestMapping("vehicle")
public class VehicleController {

    final static Logger logger = LogManager.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleGroupService vehicleGroupService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String vehicleAddView(ModelMap modelMap) {

        logger.info("add vehicle view");

        List<VehicleGroup> vehicleGroups = vehicleGroupService.getAllGroups();
        modelMap.addAttribute("vehicle", new VehicleDto());
        modelMap.addAttribute("vehicleGroups", vehicleGroups);
        return "vehicle_mgt/add_vehicle";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String vehicleAddAction(@ModelAttribute("vehicleGroups") VehicleDto vehicleDto,
                                   final RedirectAttributes redirectAttributes) {

        logger.info("add vehicle action {}", vehicleDto.getName());

        VehicleGroup vehicleGroup = vehicleGroupService.findVehicleGroupById(vehicleDto.getVehicleGroupId());
        if (vehicleGroup == null) {
            redirectAttributes.addFlashAttribute("vehicleAddSuccess", false);
            return "redirect:add";
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleDto.getName());
        vehicle.setDetails(vehicleDto.getDetails());
        vehicle.setVehicleStatus(Vehicle.Status.PENDING);
        vehicle.setVehicleGroup(vehicleGroup);

        vehicleService.addVehicle(vehicle);

        if (vehicle != null) { // saved
            redirectAttributes.addAttribute("vehicle_group_id", vehicle.getVehicleGroup().getId());
            redirectAttributes.addAttribute("authentication_code", vehicle.getAuthenticationCode());
            return "redirect:authcode";
        } else {
            logger.info("error in add vehicle action");
            redirectAttributes.addFlashAttribute("vehicleAddSuccess", false);
            return "redirect:add";
        }
    }

    @RequestMapping(value = "authcode", method = RequestMethod.GET)
    public String vehicleAuthCodeView(@RequestParam("vehicle_group_id") Long vehicleGroupId,
                                      @RequestParam("authentication_code") String authenticationCode,
                                      ModelMap modelMap) {
//        logger.info("vehicle authcode {} {}", vehicleGroupId, authenticationCode);
        logger.info("auth view");

        modelMap.addAttribute("vehicleGroupId", vehicleGroupId);
        modelMap.addAttribute("authenticationCode", authenticationCode);
        return "vehicle_mgt/auth_code";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String viewVehicles(@RequestParam(value = "vehicle_group_id", required = false) Long id,
                               ModelMap modelMap) {

        logger.info("view all vehicles, group id: {}.", id);

        List<Vehicle> vehicleList = vehicleService.getAllVehicles();

        if (id == null || id <= 0) {
            modelMap.addAttribute("vehicleList", vehicleList);
        } else {
            List<Vehicle> vehicles = new ArrayList<>(vehicleList.size());

            for (Vehicle vehicle : vehicleList) {
                if (vehicle.getVehicleGroup().getId() == id)
                    vehicles.add(vehicle);
            }
            modelMap.addAttribute("vehicleList", vehicles);
            modelMap.addAttribute("selectedVehicleGroup", id);
        }

        List<VehicleGroup> vehicleGroups = vehicleGroupService.getAllGroups();
        modelMap.addAttribute("vehicleGroups", vehicleGroups);
        return "vehicle_mgt/view_all";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String vehicleUpdateAction(Vehicle vehicle,
                                      final RedirectAttributes redirectAttributes) {

        logger.info("update vehicle", vehicle);


        vehicleService.updateVehicle(vehicle);
        return "redirect:list";

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String vehicleRemoveAction(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {


        vehicleService.remove(id);

        redirectAttributes.addFlashAttribute("VehicleDeleted", true);
        //return "vehicle_mgt/view_all";
        return "redirect:/vehicle/list";
    }

    /*    @RequestMapping(value = "map", method = RequestMethod.GET)
        public String map(ModelMap modelMap) {
            List<Vehicle> vehicleList = vehicleService.getAllVehicles();
            modelMap.addAttribute("vehicleList", vehicleList);
            List<VehicleGroup> vehicleGroups = vehicleGroupService.getAllGroups();
            modelMap.addAttribute("vehicleGroups", vehicleGroups);
            return "vehicle_mgt/map";
        }*/
    @RequestMapping(value = "map", method = RequestMethod.GET)
    public String map(@RequestParam(value = "vehicle_group_id", required = false) Long id,
                      ModelMap modelMap) {

        logger.info("view all vehicles, group id: {}.", id);

        List<Vehicle> vehicleList = vehicleService.getAllVehicles();

        if (id == null || id <= 0) {
            modelMap.addAttribute("vehicleList", vehicleList);
        } else {
            List<Vehicle> vehicles = new ArrayList<>(vehicleList.size());

            for (Vehicle vehicle : vehicleList) {
                if (vehicle.getVehicleGroup().getId() == id)
                    vehicles.add(vehicle);
            }
            modelMap.addAttribute("vehicleList", vehicles);
            modelMap.addAttribute("selectedVehicleGroup", id);
        }

        List<VehicleGroup> vehicleGroups = vehicleGroupService.getAllGroups();
        modelMap.addAttribute("vehicleGroups", vehicleGroups);
        return "vehicle_mgt/map";
    }

}
