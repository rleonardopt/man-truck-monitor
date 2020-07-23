package pt.vwds.truckmonitor.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;

@Entity
@Table(name = "trucks_location")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = TruckLocation.class)
public class TruckLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(position = 1)
    private Long id;

    @Column(name = "gpsLongitude")
    @ApiModelProperty(position = 2)
    private double gpsLongitude;

    @Column(name = "gpsLatitude")
    @ApiModelProperty(position = 2)
    private double gpsLatitude;

    public TruckLocation() {}

    public TruckLocation(Long id, double gpsLongitude, double gpsLatitude) {
        this.gpsLongitude = gpsLongitude;
        this.gpsLatitude = gpsLatitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(double gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public double getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(double gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }
}
