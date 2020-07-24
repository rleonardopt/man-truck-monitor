package pt.vwds.truckmonitor.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import pt.vwds.truckmonitor.model.Truck;
import pt.vwds.truckmonitor.model.TruckLocation;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TruckControllerITCase extends CommonITCase {

    private String baseURL;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        baseURL = "http://localhost:" + port + "/api/";
    }

    @After
    public void afterTest() {
        resetDb();
    }

    @Test
    public void whenGetAllTrucks_thenReceiveSingleTruck() {
        saveSingleTruck();

        ResponseEntity<List<Truck>> response = this.restTemplate.exchange(
                baseURL + "trucks/",
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<List<Truck>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() >= 1);
    }

    @Test
    public void whenGetSingleTruckById_thenReceiveSingleTruck() {
        Truck truck = saveSingleTruck();

        ResponseEntity<Truck> response= this.restTemplate.exchange(
                baseURL + "trucks/" + truck.getId(),
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                Truck.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(truck.getId(), response.getBody().getId());
        assertEquals(truck.getLicensePlate(), response.getBody().getLicensePlate());
    }

    @Test
    public void whenGetAllLocationsOfTruckById_thenReceiveLocationList() {
        Truck truck = saveSingleLocationOfTruck();

        ResponseEntity<List<TruckLocation>> response = this.restTemplate.exchange(
                baseURL + "trucks/" + truck.getId() + "/locations",
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<List<TruckLocation>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(truck.getTruckLocations().get(0).getId(), response.getBody().get(0).getId());
        assertEquals(truck.getTruckLocations().get(0).getGpsLatitude(), response.getBody().get(0).getGpsLatitude(), 0);
        assertEquals(truck.getTruckLocations().get(0).getGpsLongitude(), response.getBody().get(0).getGpsLongitude(), 0);

    }

    @Test
    public void whenGetSingleTruckByLicensePlate_thenReceiveSingleTruck() {
        Truck truck = saveSingleTruck();

        ResponseEntity<Truck> response = this.restTemplate.exchange(
          baseURL + "trucks?licensePlate=" + truck.getLicensePlate(),
          HttpMethod.GET,
          new HttpEntity<>(new HttpHeaders()),
          Truck.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(truck.getId(), response.getBody().getId());
        assertEquals(truck.getLicensePlate(), response.getBody().getLicensePlate());
    }

    @Test
    public void whenPostSingleTruck_thenItIsStoredInDb() {
        Truck truck = createSingleTruck();

        ResponseEntity<Truck> response = this.restTemplate.exchange(
          baseURL + "trucks/",
          HttpMethod.POST,
          new HttpEntity<>(convertTruckToDTO(truck), new HttpHeaders()), Truck.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // check response
        Truck responseTruck = response.getBody();
        assertNotNull(responseTruck.getId());
        assertEquals(truck.getLicensePlate(), responseTruck.getLicensePlate());

        // check Truck saved in db
        Truck savedTruck = findTruckInDbById(responseTruck.getId()).get();
        assertEquals(truck.getLicensePlate(), savedTruck.getLicensePlate());
    }

    @Test
    public void whenPostSingleLocationToExistingTruck_thenItIsStoredInDbAndAssignedToTruck() {

        Truck truck = saveSingleTruck();
        TruckLocation truckLocation = createSingleLocation();

        ResponseEntity<Truck> response = this.restTemplate.exchange(
          baseURL + "trucks/" + truck.getId() + "/locations",
                HttpMethod.POST,
                new HttpEntity<>(convertTruckLocationToDTO(truckLocation), new HttpHeaders()),
                Truck.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Check response Truck
        Truck resposeTruck = response.getBody();
        assertNotNull(resposeTruck.getId());
        assertEquals(truck.getLicensePlate(), resposeTruck.getLicensePlate());
        assertEquals(1, resposeTruck.getTruckLocations().size());

        // Check response TruckLocation
        TruckLocation responseTruckLocation = resposeTruck.getTruckLocations().get(0);
        assertNotNull(responseTruckLocation.getId());
        assertEquals(truckLocation.getGpsLongitude(), responseTruckLocation.getGpsLongitude(), 0);
        assertEquals(truckLocation.getGpsLatitude(), responseTruckLocation.getGpsLatitude(), 0);

        // Check saved Location in DB
        TruckLocation savedLocation = findLocationInDbByTruckId(resposeTruck.getId());
        assertEquals(responseTruckLocation.getId(), savedLocation.getId());
        assertEquals(responseTruckLocation.getGpsLongitude(), savedLocation.getGpsLongitude(), 0);
        assertEquals(responseTruckLocation.getGpsLatitude(), savedLocation.getGpsLatitude(), 0);
    }

    @Test
    public void whenPutSingleTruck_thenItIsUpdated() {
        Truck truck = saveSingleTruck();
        truck.setModel(truck.getModel() + "Updated");

        ResponseEntity<Truck> response = this.restTemplate.exchange(
          baseURL + "trucks/" + truck.getId(),
          HttpMethod.PUT,
          new HttpEntity<>(convertTruckToDTO(truck), new HttpHeaders()), Truck.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(truck.getModel(), findTruckInDbById(truck.getId()).get().getModel());
    }

    @Test
    public void whenDeleteSingleTruckById_thenItIsDeletedFromDb() {
        Truck truck = saveSingleTruck();

        ResponseEntity<String> response = this.restTemplate.exchange(
                baseURL + "trucks/" + truck.getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(new HttpHeaders()), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(String.format("Truck with id: %d was deleted", truck.getId()), response.getBody());
        assertFalse(findTruckInDbById(truck.getId()).isPresent());
    }
}
