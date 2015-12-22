package hms.service.impl;

import hms.dao.VehicleGroupDao;
import hms.dao.VehicleDao;
import hms.model.Vehicle;
import hms.model.VehicleGroup;
import hms.service.VehicleGroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Sadupa Wijeratne
 *         Date     : 12/11/14
 *         Time     : 5:22 PM
 */
@Service("vehicleGroupService")
@Transactional
public class VehicleGroupServiceImpl implements VehicleGroupService {
    @Autowired
    private VehicleGroupDao vehicleGroupDao;

    final static Logger logger = LogManager.getLogger(VehicleGroupService.class);


    @Override
    public boolean addVehicleGroup(VehicleGroup vehicleGroup) {
        return vehicleGroupDao.saveVehicleGroup(vehicleGroup);
    }

    @Override
    public List<VehicleGroup> getAllGroups() {
        logger.info("Request received to get vehicle groups");
        List<VehicleGroup> vehicleGroupList = vehicleGroupDao.getAllGroups();
        logger.info("{} vehicle groups found", vehicleGroupList.size());
        return vehicleGroupList;
    }

    @Override
    public VehicleGroup findVehicleGroupById(Long vehicleGroupId) {
        return vehicleGroupDao.findVehicleGroupById(vehicleGroupId);
    }



    @Override
    public void updateVehiclegroup(VehicleGroup vehiclegroup) {

        boolean result = vehicleGroupDao.update(vehiclegroup);
      //  return vehiclegroup;

    }

    @Override
    public void remove(Long id){

        VehicleGroup vehicleGroup = vehicleGroupDao.findVehicleGroupById(id);
        vehicleGroup.setVehicleGroupStatus(VehicleGroup.Status.REMOVED);
        vehicleGroupDao.update(vehicleGroup);


    }

}
