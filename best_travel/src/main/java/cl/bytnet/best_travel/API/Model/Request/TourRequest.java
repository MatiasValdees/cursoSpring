package cl.bytnet.best_travel.API.Model.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class TourRequest {

    private String customerId;
    private Set<TourFlyRequest> flights;
    private Set<TourHotelRequest>hotels;
    private String email;
}
