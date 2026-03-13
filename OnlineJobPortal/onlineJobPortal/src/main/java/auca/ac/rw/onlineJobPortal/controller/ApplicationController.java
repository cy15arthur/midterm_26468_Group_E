package auca.ac.rw.onlineJobPortal.controller;

import auca.ac.rw.onlineJobPortal.model.Application;
import auca.ac.rw.onlineJobPortal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable UUID id) {
        Optional<Application> application = applicationService.findById(id);
        return application.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {
        return ResponseEntity.ok(applicationService.save(application));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable UUID id, @RequestBody Application application) {
        if (!applicationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        application.setApplicationId(id);
        return ResponseEntity.ok(applicationService.save(application));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Application> patchApplication(@PathVariable UUID id, @RequestBody Application application) {
        Optional<Application> existingApplicationOpt = applicationService.findById(id);
        if (!existingApplicationOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Application existingApplication = existingApplicationOpt.get();
        if (application.getStatus() != null) existingApplication.setStatus(application.getStatus());
        if (application.getAppliedDate() != null) existingApplication.setAppliedDate(application.getAppliedDate());
        return ResponseEntity.ok(applicationService.save(existingApplication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable UUID id) {
        if (!applicationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        applicationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
