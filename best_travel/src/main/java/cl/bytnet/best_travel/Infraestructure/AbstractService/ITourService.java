package cl.bytnet.best_travel.Infraestructure.AbstractService;

import cl.bytnet.best_travel.API.Model.Request.TourRequest;
import cl.bytnet.best_travel.API.Model.Response.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCrudService<TourRequest, TourResponse,Long>{
    void deleteTicket(UUID ticketId, Long tourId);
    UUID addTicket(Long flyId,Long tourId);
    void removeReservation(UUID reservation, Long idTour);
    UUID addReservation(Long hotelId,Long tourId,Integer totalDays);

}
