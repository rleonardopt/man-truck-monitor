package pt.vwds.truckmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import pt.vwds.truckmonitor.model.Truck;
import pt.vwds.truckmonitor.model.TruckDTO;
import pt.vwds.truckmonitor.model.TruckLocation;
import pt.vwds.truckmonitor.model.TruckLocationDTO;
import pt.vwds.truckmonitor.repository.TruckRepository;

import java.util.ArrayList;
import java.util.Optional;

@TestPropertySource( properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect"
})
public class CommonITCase {

    @Autowired
    private TruckRepository truckRepository;

    protected Truck createSingleTruck() {
        Truck truck = new Truck();
        truck.setVehicleType("Buses");
        truck.setMake("MAN");
        truck.setModel("Tourist");
        truck.setSeries("Lion Coach");
        truck.setLicensePlate("00-xx-00");
        truck.setTruckLocations(new ArrayList<>());
        return truck;
    }

    protected TruckLocation createSingleLocation() {
        TruckLocation location = new TruckLocation();
        location.setGpsLatitude(38.720253);
        location.setGpsLongitude(-9.154073);
        return location;
    }

    protected Truck saveSingleTruck() {
        return truckRepository.save(createSingleTruck());
    }

    protected Truck saveSingleLocationOfTruck() {
        Truck truck = createSingleTruck();
        TruckLocation truckLocation = createSingleLocation();
        truck.addTruckLocation(truckLocation);
        return truckRepository.save(truck);
    }

    protected TruckDTO convertTruckToDTO(Truck truck) {
        TruckDTO truckDTO = new TruckDTO();
        truckDTO.setVehicleType(truck.getVehicleType());
        truckDTO.setLicensePlate(truck.getLicensePlate());
        truckDTO.setMake(truck.getMake());
        truckDTO.setModel(truck.getModel());
        truckDTO.setSeries(truck.getSeries());
        return truckDTO;
    }

    protected TruckLocationDTO convertTruckLocationToDTO(TruckLocation truckLocation) {
        TruckLocationDTO truckLocationDTO = new TruckLocationDTO();
        truckLocationDTO.setGps_latitude(truckLocation.getGpsLatitude());
        truckLocationDTO.setGps_longitude(truckLocation.getGpsLongitude());
        return truckLocationDTO;
    }

    protected Optional<Truck> findTruckInDbById(Long id) {
        return truckRepository.findById(id);
    }

    protected TruckLocation findLocationInDbByTruckId(Long id) {
        Optional<Truck> truck = findTruckInDbById(id);
        return truck.map(value -> value.getTruckLocations().get(0)).orElse(null);
    }

    protected void resetDb() {
        truckRepository.deleteAll();
    }
}
