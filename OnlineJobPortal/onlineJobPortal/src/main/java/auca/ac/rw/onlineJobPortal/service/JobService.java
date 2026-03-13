package auca.ac.rw.onlineJobPortal.service;

import auca.ac.rw.onlineJobPortal.model.Job;
import auca.ac.rw.onlineJobPortal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Optional<Job> findById(UUID id) {
        return jobRepository.findById(id);
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public void deleteById(UUID id) {
        jobRepository.deleteById(id);
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
