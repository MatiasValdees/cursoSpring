package cl.bytnet.best_travel.API.Model.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter// obtener datos json en postman
@Setter//settera propiedads en beansutil.copypropierti
public class HotelResponse implements Serializable {
    private Long id;
    private String name;
    private String address;
    private BigDecimal price;
    private Integer rating;


}
