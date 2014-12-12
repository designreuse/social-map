package hms.dto;

import hms.model.VehicleGroup;

/**
 * Created by sadika on 2014-12-12.
 */
public class VehicleGroupDto {

    String name;
    String details;
    Long startLocationId;
    Long endLocationId;

    public VehicleGroupDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getStartLocationId() {
        return startLocationId;
    }

    public void setStartLocationId(Long startLocationId) {
        this.startLocationId = startLocationId;
    }

    public Long getEndLocationId() {
        return endLocationId;
    }

    public void setEndLocationId(Long endLocationId) {
        this.endLocationId = endLocationId;
    }
}
