package cl.bytnet.best_travel.Infraestructure.AbstractService;

import cl.bytnet.best_travel.API.Model.Request.ReservationRequest;
import cl.bytnet.best_travel.API.Model.Response.ReservationResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;


public interface IReservationService extends CrudService <ReservationRequest, ReservationResponse, UUID>{
    BigDecimal findPrice(Long idHotel, Currency currency);
}
