package cl.bytnet.best_travel.Infraestructure.AbstractService;

import cl.bytnet.best_travel.Util.Enum.SortType;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Set;

public interface CatalogService <RS>{
    Page<RS> realAll(Integer page, Integer size, SortType sortType);
    Set<RS> readLessPrice(BigDecimal price);
    Set<RS> readBetweenPrice(BigDecimal min, BigDecimal max);
    String FIELD_BY_SORT="price";

}
