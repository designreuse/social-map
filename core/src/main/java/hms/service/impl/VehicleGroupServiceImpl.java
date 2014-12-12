package hms.service.impl;

import hms.dao.VehicleGroupDao;
import hms.model.VehicleGroup;
import hms.service.VehicleGroupService;
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

    @Override
    public boolean addVehicleGroup(VehicleGroup vehicleGroup) {
        return vehicleGroupDao.saveVehicleGroup(vehicleGroup);
    }

    @Override
    public List<VehicleGroup> getAllGroups() {
        return vehicleGroupDao.getAllGroups();
    }

    @Override
    public VehicleGroup findVehicleGroupById(Long vehicleGroupId) {
        return vehicleGroupDao.findVehicleGroupById(vehicleGroupId);
    }
}
