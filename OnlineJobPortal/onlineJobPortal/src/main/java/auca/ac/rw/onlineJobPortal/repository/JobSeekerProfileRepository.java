package auca.ac.rw.onlineJobPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import auca.ac.rw.onlineJobPortal.model.JobSeekerProfile;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, UUID> {
    Optional<JobSeekerProfile> findByUser_PersonId(UUID personId);
   
}
