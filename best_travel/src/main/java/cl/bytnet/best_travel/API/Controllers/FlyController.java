package cl.bytnet.best_travel.API.Controllers;

import cl.bytnet.best_travel.API.Model.Response.FlyResponse;
import cl.bytnet.best_travel.Infraestructure.AbstractService.IFlyService;
import cl.bytnet.best_travel.Util.Annotation.Notify;
import cl.bytnet.best_travel.Util.Enum.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(path = "/fly")
@AllArgsConstructor
public class FlyController {
    private final IFlyService service;

    @Notify(value = "flyRequest")
    @GetMapping
    public ResponseEntity<Page<FlyResponse>> getAll(@RequestParam Integer page,
                                                    @RequestParam Integer size,
                                                    @RequestHeader(required = false) SortType sortType){
        if (Objects.isNull(sortType))sortType=SortType.NONE;
        var response=this.service.realAll(page,size,sortType);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
    }

    @GetMapping(path = "lessPrice")
    public ResponseEntity<Set<FlyResponse>> getPrice(@RequestParam BigDecimal price){
        var response=this.service.readLessPrice(price);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
    }

    @GetMapping(path = "betweenPrice")
    public ResponseEntity<Set<FlyResponse>> getBetweenPrice(@RequestParam BigDecimal min,@RequestParam BigDecimal max){
        var response=this.service.readBetweenPrice(min,max);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
    }
    @GetMapping(path = "originDestiny")
    public ResponseEntity<Set<FlyResponse>> getByOriginDestiny(@RequestParam String origin,@RequestParam String destiny){
        var response=this.service.readByOriginDestiny(origin,destiny);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
    }

}
