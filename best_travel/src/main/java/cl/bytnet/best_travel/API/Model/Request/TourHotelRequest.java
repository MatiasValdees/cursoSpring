package cl.bytnet.best_travel.API.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TourHotelRequest {
    private long id;
    private Integer totalDays;
}
