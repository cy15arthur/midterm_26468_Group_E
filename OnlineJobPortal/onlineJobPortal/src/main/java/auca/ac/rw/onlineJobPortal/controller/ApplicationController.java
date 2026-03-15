package auca.ac.rw.onlineJobPortal.controller;

import auca.ac.rw.onlineJobPortal.model.Application;
import auca.ac.rw.onlineJobPortal.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getApplicationById(@PathVariable UUID id) {
        Optional<Application> application = applicationService.findById(id);
        if (application.isEmpty()) {
            return ResponseEntity.status(404).body("Application not found");
        }
        return ResponseEntity.ok(application.get());
    }

    @PostMapping
    public ResponseEntity<?> createApplication(@RequestBody Application application) {
        try {
            Application saved = applicationService.save(application);
            return ResponseEntity.status(201).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateApplication(@PathVariable UUID id, @RequestBody Application application) {
        try {
            return ResponseEntity.ok(applicationService.updateApplication(id, application));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchApplication(@PathVariable UUID id, @RequestBody Application application) {
        try {
            return ResponseEntity.ok(applicationService.updateApplication(id, application));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable UUID id) {
        try {
            applicationService.deleteApplication(id);
            return ResponseEntity.ok("Application deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Application not found: " + e.getMessage());
        }
    }
}
