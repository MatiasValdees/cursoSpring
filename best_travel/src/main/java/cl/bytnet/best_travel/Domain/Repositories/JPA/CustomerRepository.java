package cl.bytnet.best_travel.Domain.Repositories.JPA;

import cl.bytnet.best_travel.Domain.Entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity,String> {
}
