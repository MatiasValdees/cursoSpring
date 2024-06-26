package cl.bytnet.best_travel.Domain.Repositories.JPA;

import cl.bytnet.best_travel.Domain.Entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity,Long> {

    Set<HotelEntity>findByPriceLessThan(BigDecimal price);
    Set<HotelEntity>findByPriceBetween(BigDecimal min, BigDecimal max);
    Set<HotelEntity>findByRatingGreaterThan(Integer rating);
    Optional<HotelEntity> findByReservationsId(UUID id);
    
}
