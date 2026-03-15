package auca.ac.rw.onlineJobPortal.service;

import auca.ac.rw.onlineJobPortal.model.Job;
import auca.ac.rw.onlineJobPortal.model.User;
import auca.ac.rw.onlineJobPortal.repository.JobRepository;
import auca.ac.rw.onlineJobPortal.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Optional<Job> findById(UUID id) {
        return jobRepository.findById(id);
    }

    public Job save(Job job) {
        if (job.getRecruiter() != null && job.getRecruiter().getPersonId() != null) {
            UUID recruiterId = job.getRecruiter().getPersonId();
            User recruiter = userRepository.findById(recruiterId)
                    .orElseThrow(() -> new RuntimeException("Recruiter not found"));
            job.setRecruiter(recruiter);
        }
        return jobRepository.save(job);
    }

    public Job updateJob(UUID id, Job job) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (job.getTitle() != null) existingJob.setTitle(job.getTitle());
        if (job.getDescription() != null) existingJob.setDescription(job.getDescription());
        if (job.getRequirements() != null) existingJob.setRequirements(job.getRequirements());
        if (job.getMinSalary() != null) existingJob.setMinSalary(job.getMinSalary());
        if (job.getMaxSalary() != null) existingJob.setMaxSalary(job.getMaxSalary());
        if (job.getEmploymentType() != null) existingJob.setEmploymentType(job.getEmploymentType());
        if (job.getLocation() != null) existingJob.setLocation(job.getLocation());

        return jobRepository.save(existingJob);
    }

    public void deleteJob(UUID id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        jobRepository.delete(job);
    }

    
    public List<Job> findAllSorted(String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return jobRepository.findAll(sort);
    }


    public Page<Job> findAllPaginated(int page, int size) {
        return jobRepository.findAll(PageRequest.of(page, size));
    }

   
    public Page<Job> findAllPaginatedSorted(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return jobRepository.findAll(PageRequest.of(page, size, sort));
    }
}
