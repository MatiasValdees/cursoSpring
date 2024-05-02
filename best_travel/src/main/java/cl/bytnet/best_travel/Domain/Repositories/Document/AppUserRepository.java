package cl.bytnet.best_travel.Domain.Repositories.Document;

import cl.bytnet.best_travel.Domain.Entities.Document.AppsUserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends MongoRepository<AppsUserDocument, String> {

    Optional<AppsUserDocument> findByUsername(String username);
}
