package cl.bytnet.best_travel.Domain.Entities.Document;


import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection ="app_users")
public class AppsUserDocument {
    @Id
    private String id;
    private String username;
    private String dni;
    private boolean enabled;
    private String password;
    private Role role;

}
