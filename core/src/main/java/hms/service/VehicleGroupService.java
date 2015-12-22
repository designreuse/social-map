package hms.service;

import hms.model.VehicleGroup;

import java.util.List;

/**
 * @author : Sadupa Wijeratne
 *         Date     : 12/11/14
 *         Time     : 5:22 PM
 */
public interface VehicleGroupService {
    boolean addVehicleGroup(VehicleGroup vehicleGroup);
    List<VehicleGroup> getAllGroups();

    void updateVehiclegroup(VehicleGroup vehiclegroup);

    VehicleGroup findVehicleGroupById(Long vehicleGroupId);

    void remove(Long id);

}
