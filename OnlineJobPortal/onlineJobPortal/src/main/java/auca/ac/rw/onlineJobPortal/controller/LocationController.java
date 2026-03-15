package auca.ac.rw.onlineJobPortal.controller;

import auca.ac.rw.onlineJobPortal.enums.LocationType;
import auca.ac.rw.onlineJobPortal.model.Location;
import auca.ac.rw.onlineJobPortal.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable Long id) {
        Optional<Location> location = locationService.findById(id);
        if (location.isEmpty()) {
            return ResponseEntity.status(404).body("Location not found");
        }
        return ResponseEntity.ok(location.get());
    }

    
    @GetMapping("/provinces")
    public ResponseEntity<List<Location>> getAllProvinces() {
        return ResponseEntity.ok(locationService.findAllProvinces());
    }

  
    @GetMapping("/by-type")
    public ResponseEntity<List<Location>> getByType(@RequestParam String type) {
        return ResponseEntity.ok(locationService.findByType(LocationType.valueOf(type.toUpperCase())));
    }

    @GetMapping("/{id}/children")
    public ResponseEntity<List<Location>> getChildren(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.findChildren(id));
    }

    
    @GetMapping("/exists/{code}")
    public ResponseEntity<Boolean> existsByCode(@PathVariable String code) {
        return ResponseEntity.ok(locationService.existsByCode(code));
    }

    @PostMapping
    public ResponseEntity<?> createLocation(
            @RequestBody Location location,
            @RequestParam(required = false) Long parentId) {
        try {
            Location saved = locationService.create(location, parentId);
            return ResponseEntity.status(201).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        try {
            return ResponseEntity.ok(locationService.updateLocation(id, location));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchLocation(@PathVariable Long id, @RequestBody Location location) {
        try {
            return ResponseEntity.ok(locationService.updateLocation(id, location));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) {
        try {
            locationService.deleteLocation(id);
            return ResponseEntity.ok("Location deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Location not found: " + e.getMessage());
        }
    }
}
