package auca.ac.rw.onlineJobPortal.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import auca.ac.rw.onlineJobPortal.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

   
    @Query("SELECT u FROM User u WHERE u.village.parentLocation.parentLocation.parentLocation.parentLocation.name = :provinceName")
    List<User> findByProvinceName(@Param("provinceName") String provinceName);

    
    @Query("SELECT u FROM User u WHERE u.village.parentLocation.parentLocation.parentLocation.parentLocation.code = :provinceCode")
    List<User> findByProvinceCode(@Param("provinceCode") String provinceCode);

   
    Page<User> findAll(Pageable pageable);


    List<User> findAll(Sort sort);
}
