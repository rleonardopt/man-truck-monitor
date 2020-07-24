package pt.vwds.truckmonitor.model;

import io.swagger.annotations.ApiModelProperty;

public class TruckDTO {

    @ApiModelProperty(position = 1)
    private String vehicleType;

    @ApiModelProperty(position = 2)
    private String licensePlate;

    @ApiModelProperty(position = 3)
    private String make;

    @ApiModelProperty(position = 4)
    private String model;

    @ApiModelProperty(position = 5)
    private String series;

    public TruckDTO() {
    }

    public TruckDTO(String vehicleType, String licensePlate, String make, String model, String series) {
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.series = series;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
}
