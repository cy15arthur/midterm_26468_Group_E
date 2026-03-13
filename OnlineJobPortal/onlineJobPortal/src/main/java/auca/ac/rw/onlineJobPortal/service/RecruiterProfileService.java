package auca.ac.rw.onlineJobPortal.service;

import auca.ac.rw.onlineJobPortal.model.RecruiterProfile;
import auca.ac.rw.onlineJobPortal.repository.RecruiterProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecruiterProfileService {

    @Autowired
    private RecruiterProfileRepository recruiterProfileRepository;

    public List<RecruiterProfile> findAll() {
        return recruiterProfileRepository.findAll();
    }

    public Optional<RecruiterProfile> findById(UUID id) {
        return recruiterProfileRepository.findById(id);
    }

    public RecruiterProfile save(RecruiterProfile profile) {
        return recruiterProfileRepository.save(profile);
    }

    public void deleteById(UUID id) {
        recruiterProfileRepository.deleteById(id);
    }
}
