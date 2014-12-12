package hms.service.impl;

import hms.dao.VehicleDao;
import hms.model.Vehicle;
import hms.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

/**
 * Created by sadupa on 12/12/14.
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleDao vehicleDao;

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        //generate authentication code
        Random random = new Random();
        Integer verificationCode = random.nextInt((99999-10001) + 1) + 10001;
        vehicle.setAuthenticationCode(verificationCode.toString());
        boolean result = vehicleDao.save(vehicle);
        if (result) {
            return vehicle;
        }
        return null;
    }

    @Override
    public Vehicle getVehicleById(String vehicleId) {
        return null;
    }

    @Override
    public boolean VerifyVehicle(String vehicleId, String verificationCode) {
        return false;
    }

    @Override
    public boolean updateVehicleLocation(String vehicleId, BigDecimal longitude, BigDecimal latitude, Date time) {
        return false;
    }

    @Override
    public boolean getVehiclesByGroup(Long groupId) {
        return false;
    }
}
