package hms.dao.impl;

import hms.dao.VehicleDao;
import org.springframework.stereotype.Repository;


@Repository("vehicleDao")
public class VehicleDaoImpl extends UniversalDaoImpl implements VehicleDao {

    @Override
    public boolean save(Object object) {
        return super.save(object);
    }

    @Override
    public boolean update(Object object) {
        return super.update(object);
    }
}
