package hms.service.impl;

import hms.dao.VehicleDao;
import hms.model.Vehicle;
import hms.service.VehicleService;
import hms.util.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${max.last.updated.minutes}")
    String maxLastUpdatedMinutes;

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
    public boolean verifyVehicle(Long groupId, String vehicleId, String verificationCode) {
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
        logger.info("Request received to update vehicle location");
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
        Date minLastUpdatedTime = DateUtils.reduceMinutesFromDate(new Date(), Integer.valueOf(maxLastUpdatedMinutes));
        List<Vehicle> vehicleList = vehicleDao.getAllVehiclesByGroup(groupId, Vehicle.Status.ACTIVE, minLastUpdatedTime);
        logger.info("{} vehicles found", vehicleList.size());
        return vehicleList;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.getAllVehicles();
    }
}
