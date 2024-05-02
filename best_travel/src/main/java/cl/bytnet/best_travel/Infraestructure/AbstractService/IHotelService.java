package cl.bytnet.best_travel.Infraestructure.AbstractService;

import cl.bytnet.best_travel.API.Model.Response.HotelResponse;

import java.util.Set;

public interface IHotelService extends CatalogService<HotelResponse>{

    Set<HotelResponse> findByRating(Integer rating);
}
