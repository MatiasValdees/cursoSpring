package cl.bytnet.best_travel.API.Controllers;


import cl.bytnet.best_travel.API.Controllers.AbstractController.IControllerReservation;
import cl.bytnet.best_travel.API.Model.Request.ReservationRequest;
import cl.bytnet.best_travel.API.Model.Response.ReservationResponse;
import cl.bytnet.best_travel.Infraestructure.AbstractService.IReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/reservation")
public class ReservationController implements IControllerReservation {
    private final IReservationService service;
    @PostMapping
    @Override
    public ResponseEntity<ReservationResponse> post(@Valid @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(service.create(request));
    }
    @GetMapping(path = "{id}")
    @Override
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.read(id));
    }
    @PutMapping(path = "{id}")
    @Override
    public ResponseEntity<ReservationResponse> put(@PathVariable UUID id,@Valid @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(service.update(request,id));
    }
    @DeleteMapping(path = "{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>>getPrice(@RequestParam Long idHotel,
                                                           @RequestHeader (required = false) Currency currency){
        if(Objects.isNull(currency)) currency=Currency.getInstance("USD");
        return ResponseEntity.ok(Collections.singletonMap("HotelPrice:",this.service.findPrice(idHotel,currency)));
    }
}
