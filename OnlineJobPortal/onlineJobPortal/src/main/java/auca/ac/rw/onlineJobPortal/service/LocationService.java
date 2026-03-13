package auca.ac.rw.onlineJobPortal.service;

import auca.ac.rw.onlineJobPortal.enums.LocationType;
import auca.ac.rw.onlineJobPortal.model.Location;
import auca.ac.rw.onlineJobPortal.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    // Create with optional parentId to build hierarchy
    public Location create(Location location, Long parentId) {
        if (location.getCode() != null && locationRepository.existsByCode(location.getCode())) {
            throw new RuntimeException("Location code already exists: " + location.getCode());
        }
        if (parentId != null) {
            Location parent = locationRepository.findById(parentId)
                    .orElseThrow(() -> new RuntimeException("Parent location not found with id: " + parentId));
            location.setParentLocation(parent);
        }
        return locationRepository.save(location);
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    public boolean existsByCode(String code) {
        return locationRepository.existsByCode(code);
    }

    public List<Location> findByType(LocationType type) {
        return locationRepository.findByLocationType(type);
    }

    public List<Location> findChildren(Long parentId) {
        return locationRepository.findByParentLocation_Id(parentId);
    }

    public List<Location> findAllProvinces() {
        return locationRepository.findAllProvinces();
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
