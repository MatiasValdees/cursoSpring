package cl.bytnet.best_travel.Domain.Repositories.JPA;

import cl.bytnet.best_travel.Domain.Entities.TourEntity;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<TourEntity,Long> {
}
