package auca.ac.rw.onlineJobPortal.controller;

import auca.ac.rw.onlineJobPortal.model.Job;
import auca.ac.rw.onlineJobPortal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

   
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable UUID id) {
        Optional<Job> job = jobService.findById(id);
        return job.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        return ResponseEntity.ok(jobService.save(job));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable UUID id, @RequestBody Job job) {
        if (!jobService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        job.setJobId(id);
        return ResponseEntity.ok(jobService.save(job));
    }

   
    @PatchMapping("/{id}")
    public ResponseEntity<Job> patchJob(@PathVariable UUID id, @RequestBody Job job) {
        Optional<Job> existingJobOpt = jobService.findById(id);
        if (!existingJobOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Job existingJob = existingJobOpt.get();
        if (job.getTitle() != null) existingJob.setTitle(job.getTitle());
        if (job.getDescription() != null) existingJob.setDescription(job.getDescription());
        if (job.getRequirements() != null) existingJob.setRequirements(job.getRequirements());
        if (job.getMinSalary() != null) existingJob.setMinSalary(job.getMinSalary());
        if (job.getMaxSalary() != null) existingJob.setMaxSalary(job.getMaxSalary());
        if (job.getEmploymentType() != null) existingJob.setEmploymentType(job.getEmploymentType());
        if (job.getLocation() != null) existingJob.setLocation(job.getLocation());
        return ResponseEntity.ok(jobService.save(existingJob));
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable UUID id) {
        if (!jobService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        jobService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
