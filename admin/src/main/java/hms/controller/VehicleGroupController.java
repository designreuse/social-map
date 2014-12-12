package hms.controller;

import hms.dto.VehicleGroupDto;
import hms.model.Location;
import hms.model.VehicleGroup;
import hms.service.LocationService;
import hms.service.VehicleGroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by sadika on 2014-12-12.
 */
@Controller
@RequestMapping("vehiclegroup")
public class VehicleGroupController {

    final static Logger logger = LogManager.getLogger(VehicleGroupController.class);

    @Autowired
    VehicleGroupService vehicleGroupService;

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

        VehicleGroup vehicleGroup = new VehicleGroup();
        vehicleGroup.setName(vehicleGroupDto.getName());
        vehicleGroup.setDetails(vehicleGroupDto.getDetails());
        vehicleGroup.setStart(locationService.findLocationById(vehicleGroupDto.getStartLocationId()));
        vehicleGroup.setEnd(locationService.findLocationById(vehicleGroupDto.getEndLocationId()));

        boolean success = vehicleGroupService.addVehicleGroup(vehicleGroup);
        logger.info("saving vehicle group success: {}", success);

        redirectAttributes.addFlashAttribute("vehicleGroupAddSuccess", success);
        return "redirect:add";
    }
}
