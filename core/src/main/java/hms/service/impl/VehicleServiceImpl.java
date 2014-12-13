package hms.service.impl;

import hms.dao.VehicleDao;
import hms.model.Vehicle;
import hms.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by sadupa on 12/12/14.
 */
@Transactional
@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleDao vehicleDao;

    final static Logger logger = LogManager.getLogger(VehicleService.class);

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        //generate authentication code
        Random random = new Random();
        Integer verificationCode = random.nextInt((99999 - 10001) + 1) + 10001;
        vehicle.setAuthenticationCode(verificationCode.toString());
        boolean result = vehicleDao.save(vehicle);
        if (result) {
            return vehicle;
        }
        return null;
    }

    @Override
    public Vehicle getVehicleById(String vehicleId) {
        return vehicleDao.getVehicleById(vehicleId);
    }

    @Override
    public boolean VerifyVehicle(Long groupId, String vehicleId, String verificationCode) {
        logger.info("Request received to verify vehicle");
        Vehicle vehicle = vehicleDao.getVehicleByGroupAndCode(groupId, verificationCode, Vehicle.Status.PENDING);
        if (vehicle != null) {
            vehicle.setVehicleId(vehicleId);
            vehicle.setVehicleStatus(Vehicle.Status.ACTIVE);
            boolean result = vehicleDao.update(vehicle);
            logger.info("Vehicle verified");
            return result;
        }
        logger.info("Vehicle not verified");
        return false;
    }

    @Override
    public boolean updateVehicleLocation(String vehicleId, BigDecimal longitude, BigDecimal latitude, Date time) {
        logger.info("Request received update vehicle location");
        Vehicle vehicle = vehicleDao.getVehicleById(vehicleId);
        if (vehicle == null) {
            logger.error("Vehicle not registered {}", vehicleId);
            return false;
        }
        vehicle.setLongitude(longitude);
        vehicle.setLatitude(latitude);
        vehicle.setLastUpdatedTime(time);
        logger.info("Location updated for vehicle {}", vehicleId);
        return vehicleDao.update(vehicle);
    }

    @Override
    public List<Vehicle> getActiveVehiclesByGroup(Long groupId) {
        logger.info("Request received to get vehicles for group {}", groupId);
        List<Vehicle> vehicleList = vehicleDao.getAllVehiclesByGroup(groupId, Vehicle.Status.ACTIVE);
        logger.info("{} vehicles found", vehicleList.size());
        return vehicleList;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.getAllVehicles();
    }
}
