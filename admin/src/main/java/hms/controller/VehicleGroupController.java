package hms.controller;

import hms.dto.VehicleGroupDto;
import hms.model.Location;
import hms.model.Vehicle;
import hms.model.VehicleGroup;
import hms.service.VehicleService;
import hms.service.LocationService;
import hms.service.impl.VehicleServiceImpl;
import hms.service.VehicleGroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import hms.service.VehicleService;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sadika on 2014-12-12.
 */
@Controller
@RequestMapping("/vehiclegroup")
public class VehicleGroupController {

    final static Logger logger = LogManager.getLogger(VehicleGroupController.class);

    @Autowired
    VehicleGroupService vehicleGroupService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    LocationService locationService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String vehicleGroupAddView(ModelMap modelMap) {

        logger.info("add vehicle group view");

        List<Location> locations = locationService.getAllLocations();
        modelMap.addAttribute("vehicleGroup", new VehicleGroupDto());
        modelMap.addAttribute("locations", locations);
        return "vehicle_mgt/add_vehicle_group";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String vehicleGroupAddAction(@ModelAttribute("vehicleGroup") VehicleGroupDto vehicleGroupDto,
                                        final RedirectAttributes redirectAttributes) {

        logger.info("add vehicle group action {}", vehicleGroupDto.getName());

        Location start = locationService.findLocationById(vehicleGroupDto.getStartLocationId());
        Location end = locationService.findLocationById(vehicleGroupDto.getEndLocationId());

        if (start == null || end == null
                || start.getId() <= 0 || end.getId() <= 0
                || start.getId() == end.getId()) {

            redirectAttributes.addFlashAttribute("vehicleGroupAddSuccess", false);
            return "redirect:add";
        }

        VehicleGroup vehicleGroup = new VehicleGroup();
        vehicleGroup.setName(vehicleGroupDto.getName());
        vehicleGroup.setDetails(vehicleGroupDto.getDetails());
        vehicleGroup.setStart(start);
        vehicleGroup.setEnd(end);
        vehicleGroup.setVehicleGroupStatus(VehicleGroup.Status.ACTIVE);

        boolean success = vehicleGroupService.addVehicleGroup(vehicleGroup);
        logger.info("saving vehicle group success: {}", success);

        redirectAttributes.addFlashAttribute("vehicleGroupAddSuccess", success);
        return "redirect:add";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String viewVehicles(@RequestParam(value="vehicle_group_id", required = false) Long id,
                               ModelMap modelMap) {

        List<VehicleGroup> vehicleGroups = vehicleGroupService.getAllGroups();
        modelMap.addAttribute("vehicleGroups", vehicleGroups);
        List<Location> locations = locationService.getAllLocations();
        modelMap.addAttribute("locations", locations);
        return "vehicle_mgt/view_vehicle_group";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String editvehiclegroup(@RequestParam("id") Long id,
                           @RequestParam("name") String name,
                           @RequestParam("details") String details,
                           @RequestParam("start_location") Long start,
                           @RequestParam("end_location") Long end,
                           final RedirectAttributes redirectAttributes) {

        Location startlocation = locationService.findLocationById(start);
        Location endtlocation = locationService.findLocationById(end);

        VehicleGroup vehicleGroup = new VehicleGroup();
        vehicleGroup.setId(id);
        vehicleGroup.setName(name);
        vehicleGroup.setDetails(details);
        vehicleGroup.setStart(startlocation);
        vehicleGroup.setEnd(endtlocation);
        vehicleGroup.setVehicleGroupStatus(VehicleGroup.Status.ACTIVE);

        vehicleGroupService.updateVehiclegroup(vehicleGroup);

        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String vehicleRemoveAction(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {


        if(vehicleService.getNumberOfActiveVehiclesByGroup(id) != 0) {

            //vehicleGroupService.remove(id);
            redirectAttributes.addFlashAttribute("CouldNotDeleteVehicleGroup", false);

        }
        else{
            vehicleGroupService.remove(id);
            redirectAttributes.addFlashAttribute("CouldNotDeleteVehicleGroup", true);
        }

        return "redirect:/vehiclegroup/list";
    }

}
