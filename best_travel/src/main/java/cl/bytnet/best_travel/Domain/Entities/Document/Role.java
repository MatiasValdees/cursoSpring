package cl.bytnet.best_travel.Domain.Entities.Document;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Field(name = "granted_authorities")
    private Set<String> grantedAuthorities;
}
