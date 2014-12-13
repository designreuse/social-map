package hms.dao;


import hms.model.Vehicle;

import java.util.List;

public interface VehicleDao {

    boolean save(Vehicle vehicle);

    boolean update(Vehicle vehicle);

    Vehicle getVehicleById(String vehicleId);

    Vehicle getVehicleByGroupAndCode(Long groupId, String code, Vehicle.Status status);

    List<Vehicle> getAllVehiclesByGroup(Long groupId, Vehicle.Status status);

    List<Vehicle> getAllVehicles();

}
