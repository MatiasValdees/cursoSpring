package cl.bytnet.best_travel.Infraestructure.Service;

import cl.bytnet.best_travel.API.Model.Response.FlyResponse;
import cl.bytnet.best_travel.Domain.Entities.FlyEntity;
import cl.bytnet.best_travel.Domain.Repositories.JPA.FlyRepository;
import cl.bytnet.best_travel.Infraestructure.AbstractService.IFlyService;
import cl.bytnet.best_travel.Util.Enum.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;


@Transactional(readOnly = true)
@Service
@AllArgsConstructor
public class FlyService implements IFlyService {
    private final FlyRepository fly;
    @Override
    public Page<FlyResponse> realAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest=null;
        switch(sortType){
            case NONE -> pageRequest=PageRequest.of(page,size);
            case LOWER -> pageRequest=PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest=PageRequest.of(page,size,Sort.by(FIELD_BY_SORT).descending());
        }
        return fly.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public Set<FlyResponse> readLessPrice(BigDecimal price) {
        return fly.selectPriceLess(price).stream()
                .map(this::entityToResponse).collect(Collectors.toSet());
    }

    @Override
    public Set<FlyResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {
        return fly.selectPriceBetween(min,max).stream().map(this::entityToResponse).collect(Collectors.toSet());
    }

    @Override
    public Set<FlyResponse> readByOriginDestiny(String origen, String destiny) {
        return fly.selectForOriginAndDestiny(origen,destiny).stream().map(this::entityToResponse).collect(Collectors.toSet());
    }

    private FlyResponse entityToResponse(FlyEntity entity){
        var response=new FlyResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }
}
