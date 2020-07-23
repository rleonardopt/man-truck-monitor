package pt.vwds.truckmonitor.model;

import io.swagger.annotations.ApiModelProperty;

public class TruckLocationDTO {

    @ApiModelProperty(position = 1)
    private double gps_longitude;

    @ApiModelProperty(position = 2)
    private double gps_latitude;

    public TruckLocationDTO(double gps_longitude, double gps_latitude) {
        this.gps_longitude = gps_longitude;
        this.gps_latitude = gps_latitude;
    }

    public double getGps_longitude() {
        return gps_longitude;
    }

    public void setGps_longitude(double gps_longitude) {
        this.gps_longitude = gps_longitude;
    }

    public double getGps_latitude() {
        return gps_latitude;
    }

    public void setGps_latitude(double gps_latitude) {
        this.gps_latitude = gps_latitude;
    }
}
