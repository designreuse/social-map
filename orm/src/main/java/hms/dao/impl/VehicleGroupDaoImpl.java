package hms.dao.impl;

import hms.dao.VehicleGroupDao;
import hms.model.VehicleGroup;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author : Sadupa Wijeratne
 *         Date     : 12/11/14
 *         Time     : 5:37 PM
 */
@Repository
@Transactional
public class VehicleGroupDaoImpl extends UniversalDaoImpl implements VehicleGroupDao {
    @Override
    public boolean saveVehicleGroup(VehicleGroup vehicleGroup) {

        return super.save(vehicleGroup);
    }
}
