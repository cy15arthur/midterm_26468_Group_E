package auca.ac.rw.onlineJobPortal.service;

import auca.ac.rw.onlineJobPortal.model.User;
import auca.ac.rw.onlineJobPortal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public User save(User user) {
        if (user.getPersonId() == null && userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }
        return userRepository.save(user);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

 
    public List<User> findByProvinceName(String provinceName) {
        return userRepository.findByProvinceName(provinceName);
    }

    public List<User> findByProvinceCode(String provinceCode) {
        return userRepository.findByProvinceCode(provinceCode);
    }


    public Page<User> findAllPaginated(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public List<User> findAllSorted(String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return userRepository.findAll(sort);
    }
}
