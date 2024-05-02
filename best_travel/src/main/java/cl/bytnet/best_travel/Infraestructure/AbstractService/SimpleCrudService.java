package cl.bytnet.best_travel.Infraestructure.AbstractService;

public interface SimpleCrudService <RQ,RS,ID>{

    RS create(RQ request);
    RS read(ID id);
    void delete(ID id);
}
