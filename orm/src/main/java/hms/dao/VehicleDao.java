package hms.dao;


import hms.model.Vehicle;

import java.math.BigDecimal;

public interface VehicleDao {

    boolean save(Vehicle vehicle);

    boolean update(Vehicle vehicle);

    Vehicle getVehicleByCodeAndStatus(String code, Vehicle.Status status);

}
