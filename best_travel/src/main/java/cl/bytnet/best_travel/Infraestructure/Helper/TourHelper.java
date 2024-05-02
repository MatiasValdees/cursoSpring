package cl.bytnet.best_travel.Infraestructure.Helper;


import cl.bytnet.best_travel.Domain.Entities.*;
import cl.bytnet.best_travel.Domain.Repositories.JPA.ReservationRepository;
import cl.bytnet.best_travel.Domain.Repositories.JPA.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Transactional
@Component  //componente de un servicio

// Se encargara solamnete en la creacion de los ticket y reservacion unicamente para tour

public class TourHelper {
    private TicketRepository ticketRepository;
    private ReservationRepository reservationRepository;

    public Set<TicketEntity>createTickets(Set<FlyEntity> flights, CustomerEntity customer ){
        var response=new HashSet<TicketEntity>(flights.size());
        flights.forEach(fly->{
            var ticketToPersist=TicketEntity.builder()
                    .id(UUID.randomUUID())
                    .fly(fly)
                    .arrivalDate(LocalDateTime.now())
                    .departureDate(LocalDateTime.now())
                    .purchaseDate(LocalDateTime.now())
                    .customer(customer)
                    .price(fly.getPrice().add(fly.getPrice().multiply(BigDecimal.valueOf(0.25))))
                    .build();
            response.add(this.ticketRepository.save(ticketToPersist));
        });
        return response;
    }
    public TicketEntity createTicket(FlyEntity fly,CustomerEntity customer){
        var ticketToPersist=TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().multiply(BigDecimal.TEN))
                .purchaseDate(LocalDateTime.now())
                .arrivalDate(LocalDateTime.now())
                .departureDate(LocalDateTime.now())
                .build();
        var ticketPersisted=this.ticketRepository.save(ticketToPersist);
        return ticketRepository.save(ticketPersisted);
    }
    public Set<ReservationEntity>createReservations(HashMap<HotelEntity,Integer> hotels, CustomerEntity customer ){
        var response=new HashSet<ReservationEntity>(hotels.size());
        hotels.forEach((hotel,days)->{
            var reservationToPersist=ReservationEntity.builder()
                    .id(UUID.randomUUID())
                    .hotel(hotel)
                    .dateStart(LocalDate.now())
                    .dateEnd(LocalDate.now())
                    .dateReservation(LocalDateTime.now())
                    .totalDays(days)
                    .customer(customer)
                    .price(hotel.getPrice().add(hotel.getPrice().multiply(BigDecimal.valueOf(0.25))))
                    .build();
            response.add(this.reservationRepository.save(reservationToPersist));
        });
        return response;
    }

    public ReservationEntity createReservation(HotelEntity hotel,Integer totalDays, CustomerEntity customer){
        var reservationToPersist=ReservationEntity.builder()
                .id(UUID.randomUUID())
                .dateReservation(LocalDateTime.now())
                .dateEnd(LocalDate.now())
                .dateStart(LocalDate.now())
                .hotel(hotel)
                .customer(customer)
                .price(hotel.getPrice().add(hotel.getPrice().multiply(BigDecimal.valueOf(0.25))))
                .totalDays(totalDays)
                .build();
        return reservationRepository.save(reservationToPersist);
    }

}
