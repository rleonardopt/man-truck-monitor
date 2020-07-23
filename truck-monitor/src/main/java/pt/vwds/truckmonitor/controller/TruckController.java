package pt.vwds.truckmonitor.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.vwds.truckmonitor.model.Truck;
import pt.vwds.truckmonitor.model.TruckDTO;
import pt.vwds.truckmonitor.model.TruckLocation;
import pt.vwds.truckmonitor.model.TruckLocationDTO;
import pt.vwds.truckmonitor.service.TruckService;

import java.util.Optional;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @GetMapping("/")
    @ApiOperation(value = "View a list of all Trucks", response = Truck.class, responseContainer = "List")
    public ResponseEntity<?> getAllTrucks() {
        try {
            return new ResponseEntity<>(truckService.getAllTrucks(), HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a Truck info by its id", response = Truck.class)
    public ResponseEntity<?> getTruck(@PathVariable Long id) {
        try {
            Optional<Truck> optTruck = truckService.getTruckById(id);
            if (optTruck.isPresent()) {
                return new ResponseEntity<>(optTruck.get(), HttpStatus.OK);
            } else {
                return noTruckFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("")
    @ApiOperation(value = "Find a Truck info by its license plate", response = Truck.class)
    public ResponseEntity<?> getTruckByLicense(@RequestParam String license) {
        try {
            Optional<Truck> optTruck = truckService.getTruckByLicensePlate(license);
            if (optTruck.isPresent()) {
                return new ResponseEntity<>(optTruck.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Truck found with a license plate: " + license, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Save new Truck", response = Truck.class)
    public ResponseEntity<?> createTruck(@RequestBody TruckDTO truckDTO) {
        try {
            return new ResponseEntity<>(truckService.saveNewTruck(truckDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a Truck with specific id", response = Truck.class)
    public ResponseEntity<?> updateTruck(@PathVariable Long id, @RequestBody TruckDTO truckDTO) {
        try {
            Optional<Truck> optTruck = truckService.getTruckById(id);
            if (optTruck.isPresent()) {
                return new ResponseEntity<>(
                        truckService.updateTruck(optTruck.get(), truckDTO),
                        HttpStatus.OK);
            } else {
                return noTruckFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Delete a Truck with specific id", response = String.class)
    public ResponseEntity<?> deleteTruck(@PathVariable Long id) {
        try {
            Optional<Truck> optTruck = truckService.getTruckById(id);
            if (optTruck.isPresent()) {
                truckService.deleteTruck(optTruck.get());
                return new ResponseEntity<>(
                        String.format("Truck with id: %d was deleted", id),
                        HttpStatus.OK);
            } else {
                return noTruckFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/{truckId}/locations")
    @ApiOperation(value="View a list of all locations for a Truck with provided id", response = TruckLocation.class, responseContainer = "List")
    public ResponseEntity<?> getAllLocationsOfTruck(@PathVariable Long truckId) {
        try {
            Optional<Truck> optTruck =truckService.getTruckById(truckId);
            if (optTruck.isPresent()) {
                return new ResponseEntity<>(optTruck.get().getTruckLocations(), HttpStatus.OK);
            } else {
                return noTruckFoundResponse(truckId);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PostMapping("/{truckId}/locations")
    @ApiOperation(value="Save new location and assign it to Truck", response = Truck.class)
    public ResponseEntity<?> assignLocationOfTruck(@PathVariable Long truckId, @RequestBody TruckLocationDTO truckLocationDTO) {
        try {
            return new ResponseEntity<>(
                truckService.addNewLocationToTruck(truckId, truckLocationDTO),
                HttpStatus.CREATED);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    private ResponseEntity<String> errorResponse() {
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noTruckFoundResponse(Long id) {
        return new ResponseEntity<>("No Truck found with ID: " + id, HttpStatus.NOT_FOUND);
    }
}
