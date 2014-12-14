package hms.service;

import hms.model.Vehicle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by sadupa on 12/12/14.
 */
public interface VehicleService {
    Vehicle addVehicle(Vehicle vehicle);
    boolean verifyVehicle(Long groupId, String vehicleId, String verificationCode);
    boolean updateVehicleLocation(String vehicleId, BigDecimal longitude, BigDecimal latitude, Date time);
    List<Vehicle> getActiveVehiclesByGroup(Long groupId);
    List<Vehicle> getAllVehicles();
}
