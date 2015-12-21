package hms.dao;


import hms.model.Vehicle;

import java.util.Date;
import java.util.List;

public interface VehicleDao {

    boolean save(Vehicle vehicle);

    boolean update(Vehicle vehicle);

    Vehicle getVehicleById(String vehicleId);

    Vehicle getVehicleById(Long id);


    Vehicle getVehicleByGroupAndCode(Long groupId, String code, Vehicle.Status status);

    List<Vehicle> getAllVehiclesByGroup(Long groupId, Vehicle.Status status);

    List<Vehicle> getAllVehiclesByGroup(Long groupId, Vehicle.Status status, Date minLastUpdatedTime);

    List<Vehicle> getAllVehicles();

    List<Vehicle> getAllVehiclegroups();

   // int getVehiclecount();

    //Vehicle findVehicleGroupById(Long vehicleId);

    //int getNumberOfActiveVehiclesByGroup(Long groupId);




}
