package hms.dao.impl;

import hms.dao.LocationDao;
import hms.dao.UniversalDao;
import hms.model.Location;
import net.sf.ehcache.search.expression.Criteria;
import org.hibernate.Session;
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
}
