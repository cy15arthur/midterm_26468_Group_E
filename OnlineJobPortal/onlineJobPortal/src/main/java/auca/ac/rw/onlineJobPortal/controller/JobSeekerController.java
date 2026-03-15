package auca.ac.rw.onlineJobPortal.controller;

import auca.ac.rw.onlineJobPortal.model.JobSeekerProfile;
import auca.ac.rw.onlineJobPortal.service.JobSeekerProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/job-seeker-profiles")
public class JobSeekerController {

    private final JobSeekerProfileService jobSeekerProfileService;

    public JobSeekerController(JobSeekerProfileService jobSeekerProfileService) {
        this.jobSeekerProfileService = jobSeekerProfileService;
    }

    @GetMapping
    public ResponseEntity<List<JobSeekerProfile>> getAllProfiles() {
        return ResponseEntity.ok(jobSeekerProfileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable UUID id) {
        Optional<JobSeekerProfile> profile = jobSeekerProfileService.findById(id);
        if (profile.isEmpty()) {
            return ResponseEntity.status(404).body("JobSeekerProfile not found");
        }
        return ResponseEntity.ok(profile.get());
    }

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody JobSeekerProfile profile) {
        try {
            JobSeekerProfile saved = jobSeekerProfileService.save(profile);
            return ResponseEntity.status(201).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable UUID id, @RequestBody JobSeekerProfile profile) {
        try {
            return ResponseEntity.ok(jobSeekerProfileService.updateProfile(id, profile));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProfile(@PathVariable UUID id, @RequestBody JobSeekerProfile profile) {
        try {
            return ResponseEntity.ok(jobSeekerProfileService.updateProfile(id, profile));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable UUID id) {
        try {
            jobSeekerProfileService.deleteProfile(id);
            return ResponseEntity.ok("Job seeker profile deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Job seeker profile not found: " + e.getMessage());
        }
    }
}
