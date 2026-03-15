package auca.ac.rw.onlineJobPortal.controller;

import auca.ac.rw.onlineJobPortal.model.RecruiterProfile;
import auca.ac.rw.onlineJobPortal.service.RecruiterProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recruiter-profiles")
public class RecruiterController {

    private final RecruiterProfileService recruiterProfileService;

    public RecruiterController(RecruiterProfileService recruiterProfileService) {
        this.recruiterProfileService = recruiterProfileService;
    }

    
    @GetMapping
    public ResponseEntity<List<RecruiterProfile>> getAllProfiles() {
        return ResponseEntity.ok(recruiterProfileService.findAll());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<RecruiterProfile> getProfileById(@PathVariable UUID id) {
        return recruiterProfileService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<RecruiterProfile> createProfile(@RequestBody RecruiterProfile profile) {
        RecruiterProfile savedProfile = recruiterProfileService.createProfile(profile);
        return ResponseEntity.status(201).body(savedProfile);
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<RecruiterProfile> updateProfile(
            @PathVariable UUID id,
            @RequestBody RecruiterProfile profile) {

        RecruiterProfile updated = recruiterProfileService.updateProfile(id, profile);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

   
    @PatchMapping("/{id}")
    public ResponseEntity<RecruiterProfile> patchProfile(
            @PathVariable UUID id,
            @RequestBody RecruiterProfile profile) {

        RecruiterProfile updated = recruiterProfileService.patchProfile(id, profile);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable UUID id) {

        boolean deleted = recruiterProfileService.deleteProfile(id);
        

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}