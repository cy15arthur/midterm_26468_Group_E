package auca.ac.rw.onlineJobPortal.service;

import auca.ac.rw.onlineJobPortal.model.Location;
import auca.ac.rw.onlineJobPortal.model.User;
import auca.ac.rw.onlineJobPortal.repository.LocationRepository;
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

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public UserService(UserRepository userRepository, LocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

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

    public void deleteUser(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    public User updateUserVillage(UUID userId, Long villageId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Location village = locationRepository.findById(villageId)
                .orElseThrow(() -> new RuntimeException("Village not found"));

        user.setVillage(village);

        return userRepository.save(user);
    }

    public User updateUser(UUID id, User newUser){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(newUser.getFirstName()!=null)
            user.setFirstName(newUser.getFirstName());

        if(newUser.getLastName()!=null)
            user.setLastName(newUser.getLastName());

        if(newUser.getPhoneNumber()!=null)
            user.setPhoneNumber(newUser.getPhoneNumber());

        if(newUser.getUsername()!=null)
            user.setUsername(newUser.getUsername());

        if(newUser.getPassword()!=null)
            user.setPassword(newUser.getPassword());

        if(newUser.getRole()!=null)
            user.setRole(newUser.getRole());

        if(newUser.getDateOfBirth()!=null)
            user.setDateOfBirth(newUser.getDateOfBirth());
            
        return userRepository.save(user);
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
