package hms.dao;


import hms.model.Vehicle;

public interface VehicleDao {

    boolean save(Vehicle vehicle);

    boolean update(Vehicle vehicle);

    Vehicle getVehicleByGroupAndCode(Long groupId, String code, Vehicle.Status status);

}
