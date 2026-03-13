package auca.ac.rw.onlineJobPortal.controller;

import auca.ac.rw.onlineJobPortal.enums.LocationType;
import auca.ac.rw.onlineJobPortal.model.Location;
import auca.ac.rw.onlineJobPortal.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Optional<Location> location = locationService.findById(id);
        return location.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
            return ResponseEntity.ok(locationService.create(location, parentId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        if (!locationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        location.setId(id);
        return ResponseEntity.ok(locationService.save(location));
    }

    
    @PatchMapping("/{id}")
    public ResponseEntity<Location> patchLocation(@PathVariable Long id, @RequestBody Location location) {
        Optional<Location> existingOpt = locationService.findById(id);
        if (!existingOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Location existing = existingOpt.get();
        if (location.getName() != null) existing.setName(location.getName());
        if (location.getCode() != null) existing.setCode(location.getCode());
        if (location.getLocationType() != null) existing.setLocationType(location.getLocationType());
        if (location.getParentLocation() != null) existing.setParentLocation(location.getParentLocation());
        return ResponseEntity.ok(locationService.save(existing));
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        if (!locationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        locationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
