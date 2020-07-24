package pt.vwds.truckmonitor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.vwds.truckmonitor.model.Truck;

import java.util.Optional;

@Repository
public interface TruckRepository extends CrudRepository<Truck, Long> {

    Optional<Truck> findByLicensePlate(String licensePlate);
}
