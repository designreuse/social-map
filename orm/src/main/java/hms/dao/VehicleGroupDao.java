package hms.dao;

import hms.model.VehicleGroup;

import java.util.List;

/**
 * @author : Sadupa Wijeratne
 *         Date     : 12/11/14
 *         Time     : 5:24 PM
 */
public interface VehicleGroupDao {
    boolean saveVehicleGroup(VehicleGroup vehicleGroup);
    List<VehicleGroup> getAllGroups();

    VehicleGroup findVehicleGroupById(Long vehicleGroupId);
}
