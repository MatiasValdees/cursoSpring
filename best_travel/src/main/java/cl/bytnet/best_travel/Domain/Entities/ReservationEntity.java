package cl.bytnet.best_travel.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity(name = "reservation")
public class ReservationEntity implements Serializable {
    @Id
    private UUID id;

    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_end",nullable = true)
    private LocalDate dateEnd;

    @Column(name = "total_days")
    private Integer totalDays;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY) //Todo ManyToOne con lazy para la eliminacion
    @JoinColumn(name = "hotel_id",nullable = true)
    private HotelEntity hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private TourEntity tour;

}
