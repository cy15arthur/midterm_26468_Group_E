package auca.ac.rw.onlineJobPortal.controller;

import auca.ac.rw.onlineJobPortal.model.JobSeekerProfile;
import auca.ac.rw.onlineJobPortal.service.JobSeekerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/job-seeker-profiles")
public class JobSeekerController {

    @Autowired
    private JobSeekerProfileService jobSeekerProfileService;

    @GetMapping
    public ResponseEntity<List<JobSeekerProfile>> getAllProfiles() {
        return ResponseEntity.ok(jobSeekerProfileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSeekerProfile> getProfileById(@PathVariable UUID id) {
        Optional<JobSeekerProfile> profile = jobSeekerProfileService.findById(id);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JobSeekerProfile> createProfile(@RequestBody JobSeekerProfile profile) {
        return ResponseEntity.ok(jobSeekerProfileService.save(profile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSeekerProfile> updateProfile(@PathVariable UUID id, @RequestBody JobSeekerProfile profile) {
        if (!jobSeekerProfileService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        profile.setId(id);
        return ResponseEntity.ok(jobSeekerProfileService.save(profile));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JobSeekerProfile> patchProfile(@PathVariable UUID id, @RequestBody JobSeekerProfile profile) {
        Optional<JobSeekerProfile> existingProfileOpt = jobSeekerProfileService.findById(id);
        if (!existingProfileOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        JobSeekerProfile existingProfile = existingProfileOpt.get();
        if (profile.getSkills() != null) existingProfile.setSkills(profile.getSkills());
        if (profile.getResume() != null) existingProfile.setResume(profile.getResume());
        if (profile.getPhoto() != null) existingProfile.setPhoto(profile.getPhoto());
        return ResponseEntity.ok(jobSeekerProfileService.save(existingProfile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable UUID id) {
        if (!jobSeekerProfileService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        jobSeekerProfileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
