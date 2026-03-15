package auca.ac.rw.onlineJobPortal.service;

import auca.ac.rw.onlineJobPortal.model.JobSeekerProfile;
import auca.ac.rw.onlineJobPortal.model.User;
import auca.ac.rw.onlineJobPortal.repository.JobSeekerProfileRepository;
import auca.ac.rw.onlineJobPortal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final UserRepository userRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository, UserRepository userRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.userRepository = userRepository;
    }

    public List<JobSeekerProfile> findAll() {
        return jobSeekerProfileRepository.findAll();
    }

    public Optional<JobSeekerProfile> findById(UUID id) {
        return jobSeekerProfileRepository.findById(id);
    }

    public JobSeekerProfile save(JobSeekerProfile profile) {
        if (profile.getUser() != null && profile.getUser().getPersonId() != null) {
            UUID userId = profile.getUser().getPersonId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            profile.setUser(user);
        }
        return jobSeekerProfileRepository.save(profile);
    }

    public JobSeekerProfile updateProfile(UUID id, JobSeekerProfile profile) {
        JobSeekerProfile existing = jobSeekerProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobSeekerProfile not found"));

        if (profile.getSkills() != null) existing.setSkills(profile.getSkills());
        if (profile.getResume() != null) existing.setResume(profile.getResume());
        if (profile.getPhoto() != null) existing.setPhoto(profile.getPhoto());

        return jobSeekerProfileRepository.save(existing);
    }

    public void deleteProfile(UUID id) {
        JobSeekerProfile profile = jobSeekerProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobSeekerProfile not found"));
        jobSeekerProfileRepository.delete(profile);
    }
}
