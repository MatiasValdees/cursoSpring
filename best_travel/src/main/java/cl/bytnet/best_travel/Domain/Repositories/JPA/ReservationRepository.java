package cl.bytnet.best_travel.Domain.Repositories.JPA;

import cl.bytnet.best_travel.Domain.Entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {
}
