package auca.ac.rw.onlineJobPortal.controller;

import auca.ac.rw.onlineJobPortal.model.RecruiterProfile;
import auca.ac.rw.onlineJobPortal.service.RecruiterProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/recruiter-profiles")
public class RecruiterController {

    @Autowired
    private RecruiterProfileService recruiterProfileService;

    @GetMapping
    public ResponseEntity<List<RecruiterProfile>> getAllProfiles() {
        return ResponseEntity.ok(recruiterProfileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruiterProfile> getProfileById(@PathVariable UUID id) {
        Optional<RecruiterProfile> profile = recruiterProfileService.findById(id);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RecruiterProfile> createProfile(@RequestBody RecruiterProfile profile) {
        return ResponseEntity.ok(recruiterProfileService.save(profile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecruiterProfile> updateProfile(@PathVariable UUID id, @RequestBody RecruiterProfile profile) {
        if (!recruiterProfileService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        profile.setId(id);
        return ResponseEntity.ok(recruiterProfileService.save(profile));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RecruiterProfile> patchProfile(@PathVariable UUID id, @RequestBody RecruiterProfile profile) {
        Optional<RecruiterProfile> existingProfileOpt = recruiterProfileService.findById(id);
        if (!existingProfileOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        RecruiterProfile existingProfile = existingProfileOpt.get();
        if (profile.getCompanyName() != null) existingProfile.setCompanyName(profile.getCompanyName());
        if (profile.getCompanyDescription() != null) existingProfile.setCompanyDescription(profile.getCompanyDescription());
        if (profile.getWebsite() != null) existingProfile.setWebsite(profile.getWebsite());
        return ResponseEntity.ok(recruiterProfileService.save(existingProfile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable UUID id) {
        if (!recruiterProfileService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        recruiterProfileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
