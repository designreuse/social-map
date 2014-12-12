package hms.dto;

/**
 * Created by sadika on 2014-12-12.
 */
public class VehicleDto {
    private String name;
    private String details;
    private Long vehicleGroupId;

    public VehicleDto() {
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

    public Long getVehicleGroupId() {
        return vehicleGroupId;
    }

    public void setVehicleGroupId(Long vehicleGroupId) {
        this.vehicleGroupId = vehicleGroupId;
    }
}
