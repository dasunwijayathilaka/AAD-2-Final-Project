package lk.ijse.vehicleservice.controller;

import lk.ijse.vehicleservice.model.Vehicle;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author Dasun Wijayathilaka
 * @created 6/20/2025
 * @github https://github.com/dasunwijayathilaka
 * @project Smart-Parking-Management-System-Microservice-Based-Application
 */

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private List<Vehicle> vehicles = new ArrayList<>();

    @PostMapping("/register")
    public Vehicle registerVehicle(@RequestBody Vehicle vehicle) {
        vehicle.setVehicleId(UUID.randomUUID().toString());
        vehicle.setStatus("ACTIVE");
        vehicles.add(vehicle);
        return vehicle;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    @GetMapping("/{vehicleId}")
    public Vehicle getVehicle(@PathVariable String vehicleId) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getVehicleId().equals(vehicleId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/user/{userId}")
    public List<Vehicle> getVehiclesByUser(@PathVariable String userId) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @PutMapping("/{vehicleId}")
    public Vehicle updateVehicle(@PathVariable String vehicleId, @RequestBody Vehicle updatedVehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVehicleId().equals(vehicleId)) {
                updatedVehicle.setVehicleId(vehicleId);
                vehicles.set(i, updatedVehicle);
                return updatedVehicle;
            }
        }
        return null;
    }

    @PostMapping("/{vehicleId}/entry")
    public String simulateEntry(@PathVariable String vehicleId) {
        Vehicle vehicle = getVehicle(vehicleId);
        if (vehicle != null) {
            vehicle.setStatus("PARKED");
            return "Vehicle " + vehicle.getLicensePlate() + " entered parking area";
        }
        return "Vehicle not found";
    }

    @PostMapping("/{vehicleId}/exit")
    public String simulateExit(@PathVariable String vehicleId) {
        Vehicle vehicle = getVehicle(vehicleId);
        if (vehicle != null) {
            vehicle.setStatus("ACTIVE");
            return "Vehicle " + vehicle.getLicensePlate() + " exited parking area";
        }
        return "Vehicle not found";
    }

    @DeleteMapping("/{vehicleId}")
    public String deleteVehicle(@PathVariable String vehicleId) {
        boolean removed = vehicles.removeIf(vehicle -> vehicle.getVehicleId().equals(vehicleId));
        return removed ? "Vehicle deleted successfully" : "Vehicle not found";
    }
}