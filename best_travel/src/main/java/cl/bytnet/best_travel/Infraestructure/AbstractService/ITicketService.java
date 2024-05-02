package cl.bytnet.best_travel.Infraestructure.AbstractService;

import cl.bytnet.best_travel.API.Model.Request.TicketRequest;
import cl.bytnet.best_travel.API.Model.Response.TicketResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITicketService extends CrudService<TicketRequest, TicketResponse, UUID>{
    BigDecimal findPrice(Long idFly);

}
