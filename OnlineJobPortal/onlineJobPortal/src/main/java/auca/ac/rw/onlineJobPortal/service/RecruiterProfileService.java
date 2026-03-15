package auca.ac.rw.onlineJobPortal.service;

import auca.ac.rw.onlineJobPortal.model.RecruiterProfile;
import auca.ac.rw.onlineJobPortal.model.User;
import auca.ac.rw.onlineJobPortal.repository.RecruiterProfileRepository;
import auca.ac.rw.onlineJobPortal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;
    private final UserRepository userRepository;

    public RecruiterProfileService(
            RecruiterProfileRepository recruiterProfileRepository,
            UserRepository userRepository) {

        this.recruiterProfileRepository = recruiterProfileRepository;
        this.userRepository = userRepository;
    }

    // FIND ALL
    public List<RecruiterProfile> findAll() {
        return recruiterProfileRepository.findAll();
    }

    // FIND BY ID
    public Optional<RecruiterProfile> findById(UUID id) {
        return recruiterProfileRepository.findById(id);
    }

    // CREATE PROFILE (FIXED USER FETCH)
    public RecruiterProfile createProfile(RecruiterProfile profile) {

        UUID userId = profile.getUser().getPersonId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        profile.setUser(user);

        return recruiterProfileRepository.save(profile);
    }

    // UPDATE PROFILE
    public RecruiterProfile updateProfile(UUID id, RecruiterProfile profile) {

        Optional<RecruiterProfile> existing = recruiterProfileRepository.findById(id);

        if (!existing.isPresent()) {
            return null;
        }

        profile.setId(id);
        return recruiterProfileRepository.save(profile);
    }

    // PATCH PROFILE
    public RecruiterProfile patchProfile(UUID id, RecruiterProfile profile) {

        Optional<RecruiterProfile> existingOpt = recruiterProfileRepository.findById(id);

        if (!existingOpt.isPresent()) {
            return null;
        }

        RecruiterProfile existing = existingOpt.get();

        if (profile.getCompanyName() != null)
            existing.setCompanyName(profile.getCompanyName());

        if (profile.getCompanyDescription() != null)
            existing.setCompanyDescription(profile.getCompanyDescription());

        if (profile.getWebsite() != null)
            existing.setWebsite(profile.getWebsite());

        return recruiterProfileRepository.save(existing);
    }

    // DELETE PROFILE
    public boolean deleteProfile(UUID id) {

        Optional<RecruiterProfile> existing = recruiterProfileRepository.findById(id);

        if (!existing.isPresent()) {
            return false;
        }

        recruiterProfileRepository.deleteById(id);
        return true;
    }
}