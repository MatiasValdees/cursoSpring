package cl.bytnet.best_travel.Infraestructure.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;

@Data //verificar
public class CurrencyDTO implements Serializable {
    private LocalDate date;
//    Mapear listas dinamicas
//    private Map<Currency, BigDecimal> rates;
    private BigDecimal result;

}
