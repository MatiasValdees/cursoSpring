package cl.bytnet.best_travel.API.Controllers;


import cl.bytnet.best_travel.API.Model.Response.HotelResponse;
import cl.bytnet.best_travel.Infraestructure.AbstractService.IHotelService;
import cl.bytnet.best_travel.Util.Annotation.Notify;
import cl.bytnet.best_travel.Util.Enum.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/hotel")
public class HotelController {
    private IHotelService service;

    @Notify("hotelRequest")
    @GetMapping
    public ResponseEntity<Page<HotelResponse>> readAll(@RequestParam Integer page, @RequestParam Integer size, @RequestHeader(required = false) SortType sortType){
         var response=service.realAll(page,size,sortType);
         return ResponseEntity.ok(response);
    }

    @GetMapping(path = "lessPrice")
    public ResponseEntity<Set<HotelResponse>> realLessPrice(@RequestParam BigDecimal price){
        var response=service.readLessPrice(price);
        return  ResponseEntity.ok(response);
    }
    @GetMapping(path = "betweenPrice")
    public ResponseEntity<Set<HotelResponse>>readBetweenPrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max){
        var response=service.readBetweenPrice(min,max);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "forRating")
    public  ResponseEntity<Set<HotelResponse>>readForRating(@RequestHeader Integer rating){
        var response= service.findByRating(rating);
        return ResponseEntity.ok(response);
    }


}
