package hms.controller;

import hms.dto.VehicleDto;
import hms.model.Vehicle;
import hms.model.VehicleGroup;
import hms.service.VehicleGroupService;
import hms.service.VehicleService;
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
        for (VehicleGroup vehicleGroup : vehicleGroups) {
            logger.info("{} {} ", vehicleGroup.getName());
        }

//        modelMap.addAttribute("vehicle", new VehicleDto());
//        modelMap.addAttribute("vehicleGroups", vehicleGroups);

        logger.info("add vehicle 5555");
        return "vehicle_mgt/add_vehicle";
    }

//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String vehicleAddAction(@ModelAttribute("vehicleGroups") VehicleDto vehicleDto,
//                                   final RedirectAttributes redirectAttributes) {
//
//        logger.info("add vehicle action {}", vehicleDto.getName());
//
////        Vehicle vehicle = new Vehicle();
////        vehicle.setName(vehicleDto.getName());
////        vehicle.setDetails(vehicleDto.getDetails());
////        vehicle.setVehicleStatus(Vehicle.Status.PENDING);
////        vehicle.setVehicleGroup(vehicleGroupService.(vehicleDto.getVehicleGroupId()));
//
////        vehicleService.addVehicle(vehicle);
//
////        if (vehicle != null) { // saved
////            // show code
////        } else {
////            // show error
////        }
//
//        return "redirect:add";
//    }
}
