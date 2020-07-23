package pt.vwds.truckmonitor.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "trucks")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Truck.class)
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @ApiModelProperty(position = 1)
    private Long id;

    @Column(name = "vehicleType")
    @ApiModelProperty(position = 2)
    private String vehicleType;

    @Column(name = "licensePlate", unique = true)
    @ApiModelProperty(position = 3)
    private String licensePlate;

    @Column(name = "make")
    @ApiModelProperty(position = 4)
    private String make;

    @Column(name = "model")
    @ApiModelProperty(position = 5)
    private String model;

    @Column(name = "series")
    @ApiModelProperty(position = 6)
    private String series;

    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "truck_id")
    @ApiModelProperty(position = 7)
    private List<TruckLocation> truckLocations;

    public Truck() {

    }

    public Truck(Long id, String vehicleType, String licensePlate, String make, String model, String series, List<TruckLocation> truckLocations) {
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.series = series;
        this.truckLocations = truckLocations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TruckLocation> getTruckLocations() {
        return truckLocations;
    }

    public void setTruckLocations(List<TruckLocation> truckLocations) {
        this.truckLocations = truckLocations;
    }

    public void addTruckLocation(TruckLocation location) {
        if (Objects.isNull(truckLocations)) {
            truckLocations = new ArrayList<>();
        }
        truckLocations.add(location);
    }
}
