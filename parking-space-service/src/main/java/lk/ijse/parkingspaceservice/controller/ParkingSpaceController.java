package lk.ijse.parkingspaceservice.controller;

import lk.ijse.parkingspaceservice.model.ParkingSpace;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@RequestMapping("/api/parking-spaces")
public class ParkingSpaceController {

    private List<ParkingSpace> parkingSpaces = new ArrayList<>();

    public ParkingSpaceController() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        ParkingSpace space1 = new ParkingSpace("A001", "Colombo City Center", "Zone A", "STANDARD", 100.0, "owner1");
        space1.setSpaceId(UUID.randomUUID().toString());

        ParkingSpace space2 = new ParkingSpace("A002", "Colombo City Center", "Zone A", "COMPACT", 80.0, "owner1");
        space2.setSpaceId(UUID.randomUUID().toString());

        ParkingSpace space3 = new ParkingSpace("B001", "Liberty Plaza", "Zone B", "DISABLED", 100.0, "owner2");
        space3.setSpaceId(UUID.randomUUID().toString());

        ParkingSpace space4 = new ParkingSpace("C001", "Majestic City", "Zone C", "EV_CHARGING", 150.0, "owner3");
        space4.setSpaceId(UUID.randomUUID().toString());

        parkingSpaces.add(space1);
        parkingSpaces.add(space2);
        parkingSpaces.add(space3);
        parkingSpaces.add(space4);
    }

    @PostMapping("/create")
    public ParkingSpace createParkingSpace(@RequestBody ParkingSpace parkingSpace) {
        parkingSpace.setSpaceId(UUID.randomUUID().toString());
        parkingSpace.setStatus("AVAILABLE");
        parkingSpaces.add(parkingSpace);
        return parkingSpace;
    }

    @GetMapping
    public List<ParkingSpace> getAllParkingSpaces() {
        return parkingSpaces;
    }

    @GetMapping("/{spaceId}")
    public ParkingSpace getParkingSpace(@PathVariable String spaceId) {
        return parkingSpaces.stream()
                .filter(space -> space.getSpaceId().equals(spaceId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/available")
    public List<ParkingSpace> getAvailableParkingSpaces() {
        return parkingSpaces.stream()
                .filter(space -> "AVAILABLE".equals(space.getStatus()))
                .collect(Collectors.toList());
    }

    @GetMapping("/location/{location}")
    public List<ParkingSpace> getParkingSpacesByLocation(@PathVariable String location) {
        return parkingSpaces.stream()
                .filter(space -> space.getLocation().toLowerCase().contains(location.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/zone/{zone}")
    public List<ParkingSpace> getParkingSpacesByZone(@PathVariable String zone) {
        return parkingSpaces.stream()
                .filter(space -> space.getZone().equalsIgnoreCase(zone))
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<ParkingSpace> getParkingSpacesByOwner(@PathVariable String ownerId) {
        return parkingSpaces.stream()
                .filter(space -> space.getOwnerId().equals(ownerId))
                .collect(Collectors.toList());
    }

    @GetMapping("/type/{spaceType}")
    public List<ParkingSpace> getParkingSpacesByType(@PathVariable String spaceType) {
        return parkingSpaces.stream()
                .filter(space -> space.getSpaceType().equalsIgnoreCase(spaceType))
                .collect(Collectors.toList());
    }

    @PostMapping("/{spaceId}/reserve")
    public String reserveParkingSpace(@PathVariable String spaceId, @RequestParam String userId) {
        ParkingSpace space = getParkingSpace(spaceId);
        if (space == null) {
            return "Parking space not found";
        }

        if (!"AVAILABLE".equals(space.getStatus())) {
            return "Parking space is not available for reservation";
        }

        space.setStatus("RESERVED");
        space.setReservedBy(userId);
        space.setReservationTime(LocalDateTime.now());

        return "Parking space " + space.getSpaceNumber() + " reserved successfully for user " + userId;
    }

    @PostMapping("/{spaceId}/occupy")
    public String occupyParkingSpace(@PathVariable String spaceId, @RequestParam String vehicleId) {
        ParkingSpace space = getParkingSpace(spaceId);
        if (space == null) {
            return "Parking space not found";
        }

        if ("OCCUPIED".equals(space.getStatus())) {
            return "Parking space is already occupied";
        }

        if ("MAINTENANCE".equals(space.getStatus())) {
            return "Parking space is under maintenance";
        }

        space.setStatus("OCCUPIED");
        space.setCurrentVehicleId(vehicleId);
        space.setOccupiedSince(LocalDateTime.now());

        if ("RESERVED".equals(space.getStatus())) {
            space.setReservedBy(null);
            space.setReservationTime(null);
        }

        return "Vehicle " + vehicleId + " parked in space " + space.getSpaceNumber() + " successfully";
    }

    @PostMapping("/{spaceId}/release")
    public String releaseParkingSpace(@PathVariable String spaceId) {
        ParkingSpace space = getParkingSpace(spaceId);
        if (space == null) {
            return "Parking space not found";
        }

        if (!"OCCUPIED".equals(space.getStatus())) {
            return "Parking space is not currently occupied";
        }

        String vehicleId = space.getCurrentVehicleId();
        space.setStatus("AVAILABLE");
        space.setCurrentVehicleId(null);
        space.setOccupiedSince(null);

        return "Vehicle " + vehicleId + " released from space " + space.getSpaceNumber() + " successfully";
    }

    @PostMapping("/{spaceId}/cancel-reservation")
    public String cancelReservation(@PathVariable String spaceId) {
        ParkingSpace space = getParkingSpace(spaceId);
        if (space == null) {
            return "Parking space not found";
        }

        if (!"RESERVED".equals(space.getStatus())) {
            return "Parking space is not currently reserved";
        }

        space.setStatus("AVAILABLE");
        space.setReservedBy(null);
        space.setReservationTime(null);

        return "Reservation for space " + space.getSpaceNumber() + " cancelled successfully";
    }

    @PutMapping("/{spaceId}/status")
    public String updateParkingSpaceStatus(@PathVariable String spaceId, @RequestParam String status) {
        ParkingSpace space = getParkingSpace(spaceId);
        if (space == null) {
            return "Parking space not found";
        }

        if (!isValidStatus(status)) {
            return "Invalid status. Valid statuses are: AVAILABLE, OCCUPIED, RESERVED, MAINTENANCE";
        }

        String oldStatus = space.getStatus();
        space.setStatus(status.toUpperCase());

        if ("AVAILABLE".equals(status.toUpperCase())) {
            space.setCurrentVehicleId(null);
            space.setOccupiedSince(null);
            space.setReservedBy(null);
            space.setReservationTime(null);
        }

        return "Parking space " + space.getSpaceNumber() + " status updated from " + oldStatus + " to " + status.toUpperCase();
    }

    @PutMapping("/{spaceId}")
    public ParkingSpace updateParkingSpace(@PathVariable String spaceId, @RequestBody ParkingSpace updatedSpace) {
        for (int i = 0; i < parkingSpaces.size(); i++) {
            if (parkingSpaces.get(i).getSpaceId().equals(spaceId)) {
                updatedSpace.setSpaceId(spaceId);
                parkingSpaces.set(i, updatedSpace);
                return updatedSpace;
            }
        }
        return null;
    }

    @DeleteMapping("/{spaceId}")
    public String deleteParkingSpace(@PathVariable String spaceId) {
        boolean removed = parkingSpaces.removeIf(space -> space.getSpaceId().equals(spaceId));
        return removed ? "Parking space deleted successfully" : "Parking space not found";
    }

    @GetMapping("/stats")
    public Object getParkingSpaceStats() {
        long totalSpaces = parkingSpaces.size();
        long availableSpaces = parkingSpaces.stream().filter(s -> "AVAILABLE".equals(s.getStatus())).count();
        long occupiedSpaces = parkingSpaces.stream().filter(s -> "OCCUPIED".equals(s.getStatus())).count();
        long reservedSpaces = parkingSpaces.stream().filter(s -> "RESERVED".equals(s.getStatus())).count();
        long maintenanceSpaces = parkingSpaces.stream().filter(s -> "MAINTENANCE".equals(s.getStatus())).count();

        return new Object() {
            public final long total = totalSpaces;
            public final long available = availableSpaces;
            public final long occupied = occupiedSpaces;
            public final long reserved = reservedSpaces;
            public final long maintenance = maintenanceSpaces;
            public final double occupancyRate = totalSpaces > 0 ? (double) occupiedSpaces / totalSpaces * 100 : 0;
        };
    }

    private boolean isValidStatus(String status) {
        return status != null &&
                ("AVAILABLE".equalsIgnoreCase(status) ||
                        "OCCUPIED".equalsIgnoreCase(status) ||
                        "RESERVED".equalsIgnoreCase(status) ||
                        "MAINTENANCE".equalsIgnoreCase(status));
    }
}