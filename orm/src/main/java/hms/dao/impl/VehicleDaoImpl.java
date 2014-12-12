package hms.dao.impl;

import hms.dao.VehicleDao;
import hms.model.Vehicle;
import org.springframework.stereotype.Repository;


@Repository("vehicleDao")
public class VehicleDaoImpl extends UniversalDaoImpl implements VehicleDao {

    @Override
    public boolean save(Vehicle vehicle) {
        return super.save(vehicle);
    }

    @Override
    public boolean update(Vehicle vehicle) {
        return super.update(vehicle);
    }
}
