package cl.bytnet.best_travel.Infraestructure.AbstractService;
//Interface generica para los DTO
public interface CrudService <RQ,RS,ID>{
    RS create(RQ request); //return respuesta necesitara una solicitud

    RS read(ID id);

    RS update(RQ request, ID id);

    void delete(ID id);

}
