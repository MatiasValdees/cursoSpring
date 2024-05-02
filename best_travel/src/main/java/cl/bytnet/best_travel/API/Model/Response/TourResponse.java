package cl.bytnet.best_travel.API.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Setter
@Builder
public class TourResponse {
    private Long id;
    private Set<UUID>ticketsId;
    private Set<UUID> reservationsId;
}
