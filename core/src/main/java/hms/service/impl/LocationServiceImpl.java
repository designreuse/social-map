package hms.service.impl;

import hms.dao.LocationDao;
import hms.model.Location;
import hms.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service("locationService")
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationDao locationDao;

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public Location findLocationById(Long locationId) {
        return locationDao.findLocationById(locationId);
    }
}
