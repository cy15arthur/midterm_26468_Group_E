package auca.ac.rw.onlineJobPortal.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.ac.rw.onlineJobPortal.enums.EmploymentType;
import auca.ac.rw.onlineJobPortal.enums.LocationEnum;
import auca.ac.rw.onlineJobPortal.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {


    List<Job> findByCategories_CategoryId(UUID categoryId);
    List<Job> findByTitleContainingIgnoreCase(String keyword);
    List<Job> findByRecruiter_personId(UUID personId);
    List<Job> findByLocation(LocationEnum location);
    List<Job> findByEmploymentType(EmploymentType employmentType);

  
    Page<Job> findAll(Pageable pageable);

    
    List<Job> findAll(Sort sort);
}
