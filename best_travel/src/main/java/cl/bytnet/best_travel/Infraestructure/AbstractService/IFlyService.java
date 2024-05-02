package cl.bytnet.best_travel.Infraestructure.AbstractService;

import cl.bytnet.best_travel.API.Model.Response.FlyResponse;

import java.util.Set;

public interface IFlyService extends CatalogService<FlyResponse> {
    Set<FlyResponse> readByOriginDestiny(String origen, String destiny);
}
