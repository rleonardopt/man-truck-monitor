package pt.vwds.truckmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.vwds.truckmonitor.model.Truck;
import pt.vwds.truckmonitor.model.TruckDTO;
import pt.vwds.truckmonitor.model.TruckLocation;
import pt.vwds.truckmonitor.model.TruckLocationDTO;
import pt.vwds.truckmonitor.repository.TruckRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TruckServiceImpl implements TruckService {

    @Autowired
    private TruckRepository truckRepository;

    @Override
    @Transactional
    public List<Truck> getAllTrucks() {
        List<Truck> truckList = new ArrayList<>();
        truckRepository.findAll().forEach(truckList::add);
        return truckList;
    }

    @Override
    @Transactional
    public Optional<Truck> getTruckById(Long id) {
        return truckRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Truck> getTruckByLicensePlate(String license) {
        return truckRepository.findByLicensePlate(license);
    }

    @Override
    @Transactional
    public Truck saveNewTruck(TruckDTO truckDTO) {
        return truckRepository.save(convertDTOtoTruck(truckDTO));
    }

    @Override
    @Transactional
    public Truck updateTruck(Truck oldTruck, TruckDTO newTruckDTO) {
        oldTruck.setVehicleType(newTruckDTO.getVehicleType());
        oldTruck.setLicensePlate(newTruckDTO.getLicensePlate());
        oldTruck.setModel(newTruckDTO.getModel());
        oldTruck.setSeries(newTruckDTO.getSeries());
        return truckRepository.save(oldTruck);
    }

    @Override
    @Transactional
    public void deleteTruck(Truck truck) {
        truckRepository.delete(truck);
    }

    @Override
    @Transactional
    public Truck addNewLocationToTruck(Long truckId, TruckLocationDTO truckLocationDTO) {
        Truck truck = truckRepository.findById(truckId).get();
        truck.addTruckLocation(convertDTOtoLocation(truckLocationDTO));
        return truckRepository.save(truck);
    }

    private Truck convertDTOtoTruck(TruckDTO truckDTO) {
        Truck truck = new Truck();
        truck.setVehicleType(truckDTO.getVehicleType());
        truck.setSeries(truckDTO.getSeries());
        truck.setModel(truckDTO.getModel());
        truck.setLicensePlate(truckDTO.getLicensePlate());
        truck.setMake(truckDTO.getMake());
        return truck;
    }

    private TruckLocation convertDTOtoLocation(TruckLocationDTO truckLocationDTO) {
        TruckLocation truckLocation = new TruckLocation();
        truckLocation.setGpsLatitude(truckLocationDTO.getGps_latitude());
        truckLocation.setGpsLongitude(truckLocationDTO.getGps_longitude());
        return truckLocation;
    }
}
