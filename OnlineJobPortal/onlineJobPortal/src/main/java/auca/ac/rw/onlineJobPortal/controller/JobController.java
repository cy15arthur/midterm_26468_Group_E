package auca.ac.rw.onlineJobPortal.controller;

import auca.ac.rw.onlineJobPortal.model.Job;
import auca.ac.rw.onlineJobPortal.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

   
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable UUID id) {
        Optional<Job> job = jobService.findById(id);
        if (job.isEmpty()) {
            return ResponseEntity.status(404).body("Job not found");
        }
        return ResponseEntity.ok(job.get());
    }

    
    @GetMapping("/sorted")
    public ResponseEntity<List<Job>> getSortedJobs(
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(jobService.findAllSorted(sortBy, direction));
    }

    
    @GetMapping("/paged")
    public ResponseEntity<Page<Job>> getPagedJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(jobService.findAllPaginated(page, size));
    }

   
    @GetMapping("/paged-sorted")
    public ResponseEntity<Page<Job>> getPagedSortedJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(jobService.findAllPaginatedSorted(page, size, sortBy, direction));
    }

 
    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody Job job) {
        try {
            Job saved = jobService.save(job);
            return ResponseEntity.status(201).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable UUID id, @RequestBody Job job) {
        try {
            return ResponseEntity.ok(jobService.updateJob(id, job));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

   
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchJob(@PathVariable UUID id, @RequestBody Job job) {
        try {
            return ResponseEntity.ok(jobService.updateJob(id, job));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable UUID id) {
        try {
            jobService.deleteJob(id);
            return ResponseEntity.ok("Job deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Job not found: " + e.getMessage());
        }
    }
}
