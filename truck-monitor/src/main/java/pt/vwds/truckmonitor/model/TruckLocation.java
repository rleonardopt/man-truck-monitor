package pt.vwds.truckmonitor.model;

import javax.persistence.*;

@Entity
@Table(name = "trucks_location")
public class TruckLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long gps_longitude;
    private long gps_latitude;

    public TruckLocation(Long id, long gps_longitude, long gps_latitude) {
        this.id = id;
        this.gps_longitude = gps_longitude;
        this.gps_latitude = gps_latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getGps_longitude() {
        return gps_longitude;
    }

    public void setGps_longitude(long gps_longitude) {
        this.gps_longitude = gps_longitude;
    }

    public long getGps_latitude() {
        return gps_latitude;
    }

    public void setGps_latitude(long gps_latitude) {
        this.gps_latitude = gps_latitude;
    }
}
