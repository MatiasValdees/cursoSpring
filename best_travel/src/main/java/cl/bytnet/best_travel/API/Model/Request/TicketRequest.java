package cl.bytnet.best_travel.API.Model.Request;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequest {
    private  String idClient;
    private Long idFly;
    @Email
    private String email;
}
