package auca.ac.rw.onlineJobPortal.repository;

import auca.ac.rw.onlineJobPortal.enums.LocationType;
import auca.ac.rw.onlineJobPortal.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByCode(String code);

    boolean existsByCode(String code);

    List<Location> findByLocationType(LocationType locationType);

    List<Location> findByParentLocation_Id(Long parentId);

    @Query("SELECT l FROM Location l WHERE l.locationType = 'PROVINCE'")
    List<Location> findAllProvinces();
}
