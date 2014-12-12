package hms.dao.impl;

import hms.dao.VehicleDao;
import hms.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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

    @Override
    public Vehicle getVehicleByCodeAndStatus(String code, Vehicle.Status status) {
        Session session = getSession();
        Vehicle result = (Vehicle) session.createCriteria(Vehicle.class)
                .add(Restrictions.eq("authenticationCode", code))
                .add(Restrictions.eq("vehicleStatus", status))
                .uniqueResult();
        return result;
    }
}
