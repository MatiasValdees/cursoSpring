package cl.bytnet.best_travel.API.Model.Request;

import cl.bytnet.best_travel.API.Model.Response.HotelResponse;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ReservationRequest {
    @Size(min = 18,max = 20)
    @NotBlank
    private String idClient;
    @Positive
    @NotNull
    private Long idHotel;
    @Min(1)
    @Max(30)
    private Integer totalDays;
    @Email
    private String email;
}
