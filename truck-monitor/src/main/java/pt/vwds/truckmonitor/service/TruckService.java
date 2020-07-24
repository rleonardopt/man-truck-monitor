package pt.vwds.truckmonitor.service;

import pt.vwds.truckmonitor.model.Truck;
import pt.vwds.truckmonitor.model.TruckDTO;
import pt.vwds.truckmonitor.model.TruckLocationDTO;

import java.util.List;
import java.util.Optional;

public interface TruckService {

    List<Truck> getAllTrucks();

    Optional<Truck> getTruckById(Long id);

    Optional<Truck> getTruckByLicensePlate(String licensePlate);

    Truck saveNewTruck(TruckDTO truckDTO);

    Truck updateTruck(Truck oldTruck, TruckDTO newTruckDTO);

    void deleteTruck(Truck truck);

    Truck addNewLocationToTruck(Long truck, TruckLocationDTO truckLocationDTO);
}
