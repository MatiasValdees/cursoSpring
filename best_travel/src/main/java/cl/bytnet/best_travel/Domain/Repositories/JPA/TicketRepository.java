package cl.bytnet.best_travel.Domain.Repositories.JPA;

import cl.bytnet.best_travel.Domain.Entities.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository extends CrudRepository<TicketEntity, UUID> {
}
