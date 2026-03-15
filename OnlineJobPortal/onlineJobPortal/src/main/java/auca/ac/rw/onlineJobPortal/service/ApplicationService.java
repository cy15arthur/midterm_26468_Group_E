package auca.ac.rw.onlineJobPortal.service;

import auca.ac.rw.onlineJobPortal.model.Application;
import auca.ac.rw.onlineJobPortal.model.Job;
import auca.ac.rw.onlineJobPortal.model.User;
import auca.ac.rw.onlineJobPortal.repository.ApplicationRepository;
import auca.ac.rw.onlineJobPortal.repository.JobRepository;
import auca.ac.rw.onlineJobPortal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public Optional<Application> findById(UUID id) {
        return applicationRepository.findById(id);
    }

    public Application save(Application application) {
        if (application.getJobSeeker() != null && application.getJobSeeker().getPersonId() != null) {
            UUID userId = application.getJobSeeker().getPersonId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Job Seeker not found"));
            application.setJobSeeker(user);
        }
        if (application.getJob() != null && application.getJob().getJobId() != null) {
            UUID jobId = application.getJob().getJobId();
            Job job = jobRepository.findById(jobId)
                    .orElseThrow(() -> new RuntimeException("Job not found"));
            application.setJob(job);
        }
        return applicationRepository.save(application);
    }

    public Application updateApplication(UUID id, Application application) {
        Application existingApplication = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (application.getStatus() != null) existingApplication.setStatus(application.getStatus());
        if (application.getAppliedDate() != null) existingApplication.setAppliedDate(application.getAppliedDate());

        return applicationRepository.save(existingApplication);
    }

    public void deleteApplication(UUID id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        applicationRepository.delete(application);
    }
}
