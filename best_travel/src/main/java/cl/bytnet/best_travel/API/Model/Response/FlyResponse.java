package cl.bytnet.best_travel.API.Model.Response;

import cl.bytnet.best_travel.Util.Enum.Aeroline;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class FlyResponse {
    private Long id;
    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;
    private String originName;
    private String destinyName;
    private BigDecimal price;
    private Aeroline aeroLine;
}
