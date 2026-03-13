package auca.ac.rw.onlineJobPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import auca.ac.rw.onlineJobPortal.model.Application;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    List<Application> findByJob_JobId(UUID jobId);
    List<Application> findByJobSeeker_PersonId(UUID personId);
    boolean existsByJobSeeker_PersonIdAndJob_JobId(UUID jobSeekerId, UUID jobId);
}
