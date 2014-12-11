package hms.service.impl;

import hms.model.VehicleGroup;
import hms.service.VehicleGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Sadupa Wijeratne
 *         Date     : 12/11/14
 *         Time     : 5:22 PM
 */
@Service
@Transactional
public class VehicleGroupServiceImpl implements VehicleGroupService {
    @Override
    public boolean addVehicleGroup(VehicleGroup vehicleGroup) {
        return false;
    }
}
