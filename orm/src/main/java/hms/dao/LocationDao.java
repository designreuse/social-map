package hms.dao;

import hms.model.Location;

import java.util.List;

/**
 * Created by ishara on 12/11/14.
 */
public interface LocationDao {

    List<Location> getAllLocations();

    Location findLocationById(Long locationId);
}
