package pt.vwds.truckmonitor.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import pt.vwds.truckmonitor.model.Truck;
import pt.vwds.truckmonitor.repository.TruckRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TruckServiceTest {


    TruckService truckService;

    @Mock
    TruckRepository truckRepository;

    @Before
    public void setUp() {
        truckService = new TruckServiceImpl(truckRepository);
    }

    @Test
    public void when2TrucksInDatabase_thenGetListWithAllOfThem() {
        mockTruckInDatabase(2);

        List<Truck> trucks = truckService.getAllTrucks();

        assertEquals(2, trucks.size());
    }

    private void mockTruckInDatabase(int truckCount) {
        when(truckRepository.findAll())
                .thenReturn(createTruckList(truckCount));
    }

    private List<Truck> createTruckList(int truckCount) {
        List<Truck> trucks = new ArrayList<>();
        IntStream.range(0, truckCount).forEach(number -> {
            Truck truck = new Truck();
            truck.setId((long) number);
            truck.setVehicleType("Buses" + number);
            truck.setMake("MAN");
            truck.setModel("Tourist");
            truck.setSeries("Lion Coach");
            truck.setLicensePlate("00-xx-0" + number);
            truck.setTruckLocations(new ArrayList<>());
            trucks.add(truck);
        });
        return trucks;
    }
}