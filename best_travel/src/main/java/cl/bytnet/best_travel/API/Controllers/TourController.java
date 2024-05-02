package cl.bytnet.best_travel.API.Controllers;


import cl.bytnet.best_travel.API.Model.Request.TourRequest;
import cl.bytnet.best_travel.API.Model.Response.TourResponse;
import cl.bytnet.best_travel.Infraestructure.AbstractService.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/tour")
@AllArgsConstructor

public class TourController {
    private final ITourService service;
    @PostMapping
    public ResponseEntity<TourResponse>post(@RequestBody TourRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TourResponse>read(@PathVariable Long id){
        return ResponseEntity.ok(service.read(id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping(path = "{tourId}/remove_ticket/{ticketId}")
    public ResponseEntity<Void>deleteTicket(@PathVariable Long tourId, @PathVariable UUID ticketId){
        service.deleteTicket(ticketId,tourId);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping(path = "{tourId}/add_ticket/{flyId}")
    public ResponseEntity<Map<String,UUID>>postTicket(@PathVariable Long tourId, @PathVariable Long flyId){
        var response= Collections.singletonMap("TicketId",service.addTicket(flyId,tourId));
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "{tourId}/remove_reservation/{ReservationId}")
    public ResponseEntity<Void>deleteReservation(@PathVariable Long tourId, @PathVariable UUID ReservationId){
        service.removeReservation(ReservationId,tourId);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping(path = "{tourId}/add_reservation/{hotelId}")
    public ResponseEntity<Map<String,UUID>>postReservation(@PathVariable Long tourId, @PathVariable Long hotelId,@RequestParam Integer totalDays){
        var response= Collections.singletonMap("reservationId:",service.addReservation(hotelId,tourId,totalDays ));
        return ResponseEntity.ok(response);
    }

}
