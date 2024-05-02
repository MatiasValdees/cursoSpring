package cl.bytnet.best_travel.Infraestructure.Service;

import cl.bytnet.best_travel.API.Model.Response.HotelResponse;
import cl.bytnet.best_travel.Domain.Entities.HotelEntity;
import cl.bytnet.best_travel.Domain.Repositories.JPA.HotelRepository;
import cl.bytnet.best_travel.Infraestructure.AbstractService.IHotelService;
import cl.bytnet.best_travel.Util.Cache;
import cl.bytnet.best_travel.Util.Enum.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
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
public class HotelService implements IHotelService {
    private final HotelRepository repository;
    @Override
    public Page<HotelResponse> realAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest=null;
        switch (sortType){
            case NONE ->pageRequest=PageRequest.of(page,size);
            case LOWER -> pageRequest=PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest=PageRequest.of(page,size,Sort.by(FIELD_BY_SORT).descending());
        }
        return repository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    @Cacheable(value = Cache.HOTEL_CACHE_NAME)
    public Set<HotelResponse> readLessPrice(BigDecimal price) {
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            System.out.println("NOOOOOOOOOOOOO"+e);
        }
        return repository.findByPriceLessThan(price).stream().map(this::entityToResponse).collect(Collectors.toSet());
    }

    @Override
    @Cacheable(value = Cache.HOTEL_CACHE_NAME)
    public Set<HotelResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            System.out.println("NOOOOOOOOOOOOO"+e);
        }
        return repository.findByPriceBetween(min,max).stream().map(this::entityToResponse).collect(Collectors.toSet());
    }

    @Override
    @Cacheable(value = Cache.HOTEL_CACHE_NAME)
    public Set<HotelResponse> findByRating(Integer rating) {
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            System.out.println("NOOOOOOOOOOOOO"+e);
        }
        return repository.findByRatingGreaterThan(rating).stream().map(this::entityToResponse).collect(Collectors.toSet());
    }

    private HotelResponse entityToResponse(HotelEntity entity){
        var response=new HotelResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }
}
