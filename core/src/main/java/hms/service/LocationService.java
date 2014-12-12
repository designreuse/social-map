package hms.service;


import hms.model.Location;

import java.util.List;

public interface LocationService {

    List<Location> getAllLocations();

    Location findLocationById(Long locationId);
}
