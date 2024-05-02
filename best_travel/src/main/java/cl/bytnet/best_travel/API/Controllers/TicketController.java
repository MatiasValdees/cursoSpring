package cl.bytnet.best_travel.API.Controllers;

import cl.bytnet.best_travel.API.Model.Request.TicketRequest;
import cl.bytnet.best_travel.API.Model.Response.TicketResponse;
import cl.bytnet.best_travel.Infraestructure.AbstractService.ITicketService;
import jdk.jfr.Frequency;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/ticket")
public class TicketController {

    private final ITicketService ticketService;

    //Metodo recibe parametros desde body (objeto JSON)
    @PostMapping
    public ResponseEntity<TicketResponse> post (@RequestBody TicketRequest request){
        return ResponseEntity.ok(ticketService.create(request));
    }

    //Metodo recibe parametros por URL
    @GetMapping(path = "{id}")
    public ResponseEntity<TicketResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(ticketService.read(id));
    }
    @PutMapping(path="{id}")
    public ResponseEntity<TicketResponse> put(@PathVariable UUID id, @RequestBody TicketRequest request){
        return ResponseEntity.ok(ticketService.update(request, id));
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void>delete(@PathVariable UUID id){
        this.ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>>getFlyPrice(@RequestParam Long flyId){
        return ResponseEntity.ok(Collections.singletonMap("flyPrice: ",this.ticketService.findPrice(flyId)));

    }


}
