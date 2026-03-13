package auca.ac.rw.onlineJobPortal.service;

import auca.ac.rw.onlineJobPortal.model.JobSeekerProfile;
import auca.ac.rw.onlineJobPortal.repository.JobSeekerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobSeekerProfileService {

    @Autowired
    private JobSeekerProfileRepository jobSeekerProfileRepository;

    public List<JobSeekerProfile> findAll() {
        return jobSeekerProfileRepository.findAll();
    }

    public Optional<JobSeekerProfile> findById(UUID id) {
        return jobSeekerProfileRepository.findById(id);
    }

    public JobSeekerProfile save(JobSeekerProfile profile) {
        return jobSeekerProfileRepository.save(profile);
    }

    public void deleteById(UUID id) {
        jobSeekerProfileRepository.deleteById(id);
    }
}
