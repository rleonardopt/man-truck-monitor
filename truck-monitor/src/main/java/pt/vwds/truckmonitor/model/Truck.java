package pt.vwds.truckmonitor.model;

import javax.persistence.*;

@Entity
@Table(name = "trucks")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String vehicle_type;
    private String licence_plate;
    private String make;
    private String model;
    private String series;

    public Truck() {
    }

    public Truck(Long id, String vehicle_type, String licence_plate, String make, String model, String series) {
        this.id = id;
        this.vehicle_type = vehicle_type;
        this.licence_plate = licence_plate;
        this.make = make;
        this.model = model;
        this.series = series;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getLicence_plate() {
        return licence_plate;
    }

    public void setLicence_plate(String licence_plate) {
        this.licence_plate = licence_plate;
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
