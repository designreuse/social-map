package hms.dao.impl;

import hms.dao.LocationDao;
import hms.dao.UniversalDao;
import hms.model.Location;
import net.sf.ehcache.search.expression.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository("locationDao")
public class LocationDaoImpl extends UniversalDaoImpl implements LocationDao {

    @Override
    public List<Location> getAllLocations() {
        Session session = getSession();
        List<Location> result = session.createCriteria(Location.class)
                .setResultTransformer(org.hibernate.Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        return result;

    }

    @Override
    public Location findLocationById(Long locationId) {
        Session session = getSession();
        Location result = (Location) session.createCriteria(Location.class)
                .add(Restrictions.eq("id", locationId))
                .uniqueResult();
        return result;
    }
}
