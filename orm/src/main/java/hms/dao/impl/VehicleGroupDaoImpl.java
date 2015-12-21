package hms.dao.impl;

import hms.dao.VehicleGroupDao;
import hms.model.VehicleGroup;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Sadupa Wijeratne
 *         Date     : 12/11/14
 *         Time     : 5:37 PM
 */
@Repository("vehicleGroupDao")
public class VehicleGroupDaoImpl extends UniversalDaoImpl implements VehicleGroupDao {
    @Override
    public boolean saveVehicleGroup(VehicleGroup vehicleGroup) {
        return super.save(vehicleGroup);
    }

    @Override
    public List<VehicleGroup> getAllGroups() {
        Session session = getSession();
        List<VehicleGroup> result = session.createCriteria(VehicleGroup.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.ne("vehicleGroupStatus", VehicleGroup.Status.REMOVED))
                .list();
        return result;
    }
    @Override
    public boolean update(VehicleGroup vehiclegroup) {
        return super.update(vehiclegroup);
    }
    @Override
    public VehicleGroup findVehicleGroupById(Long vehicleGroupId) {
        Session session = getSession();
        VehicleGroup result = (VehicleGroup) session.createCriteria(VehicleGroup.class)
                .add(Restrictions.eq("id", vehicleGroupId))
                .uniqueResult();
        return result;
    }

}
