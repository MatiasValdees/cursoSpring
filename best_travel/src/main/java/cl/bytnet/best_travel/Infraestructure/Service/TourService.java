package cl.bytnet.best_travel.Infraestructure.Service;

import cl.bytnet.best_travel.API.Model.Request.TourRequest;
import cl.bytnet.best_travel.API.Model.Response.TourResponse;
import cl.bytnet.best_travel.Domain.Entities.*;
import cl.bytnet.best_travel.Domain.Repositories.JPA.CustomerRepository;
import cl.bytnet.best_travel.Domain.Repositories.JPA.FlyRepository;
import cl.bytnet.best_travel.Domain.Repositories.JPA.HotelRepository;
import cl.bytnet.best_travel.Domain.Repositories.JPA.TourRepository;
import cl.bytnet.best_travel.Infraestructure.AbstractService.ITourService;
import cl.bytnet.best_travel.Infraestructure.Helper.CustomerHelper;
import cl.bytnet.best_travel.Infraestructure.Helper.EmailHelper;
import cl.bytnet.best_travel.Infraestructure.Helper.TourHelper;
import cl.bytnet.best_travel.Util.Exception.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@Transactional
@Service
@AllArgsConstructor
public class TourService implements ITourService {
    private final TourRepository tourRepository;
    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;
    private final TourHelper tourHelper;
    private final CustomerHelper customerHelper;
    private final EmailHelper emailHelper;

    @Override
    public void deleteTicket(UUID ticketId, Long tourId) {
        var tourUpdate=tourRepository.findById(tourId).orElseThrow(()->new IdNotFoundException("tour"));
        tourUpdate.removeTicket(ticketId);
        tourRepository.save(tourUpdate);
    }
    @Override
    public UUID addTicket(Long flyId, Long tourId) {
        var tourUpdate=tourRepository.findById(tourId).orElseThrow(()->new IdNotFoundException("hotel"));
        var fly=flyRepository.findById(flyId).orElseThrow(()->new IdNotFoundException("fly"));
        var ticket=tourHelper.createTicket(fly,tourUpdate.getCustomer());
        tourUpdate.addTicket(ticket);
        tourRepository.save(tourUpdate);
        return ticket.getId();
    }
    @Override
    public void removeReservation(UUID reservation, Long idTour) {
        var tourUpdate=tourRepository.findById(idTour).orElseThrow(()->new IdNotFoundException("tour"));
        tourUpdate.removeReservation(reservation);
        tourRepository.save(tourUpdate);
    }

    @Override
    public UUID addReservation(Long hotelId, Long tourId,Integer totalDays) {
        var tourUpdate=tourRepository.findById(tourId).orElseThrow(()->new IdNotFoundException("tour"));
        var hotel=hotelRepository.findById(hotelId).orElseThrow(()->new IdNotFoundException("hotel"));
        var reservation=tourHelper.createReservation(hotel,totalDays,tourUpdate.getCustomer());
        tourUpdate.addReservation(reservation);
        tourRepository.save(tourUpdate);
        return reservation.getId();
    }

    @Override
    public TourResponse create(TourRequest request) {
        var customer=customerRepository.findById(request.getCustomerId()).orElseThrow(()->new IdNotFoundException("customer"));
        var flights=new HashSet<FlyEntity>();
        request.getFlights().forEach(fly->flights.add(flyRepository.findById(fly.getId()).orElseThrow(()->new IdNotFoundException("fly"))));
        var hotels=new HashMap<HotelEntity,Integer>();
        request.getHotels().forEach(hotel->hotels.put(hotelRepository.findById(hotel.getId()).orElseThrow(()->new IdNotFoundException("hotel")),hotel.getTotalDays()));
        var tourToSave= TourEntity.builder()
                .tickets(this.tourHelper.createTickets(flights,customer))
                .reservations(this.tourHelper.createReservations(hotels,customer))
                .customer(customer)
                .build();
        var tourSaved=tourRepository.save(tourToSave);
        customerHelper.incrase(customer.getDni(), TourService.class);
        if (Objects.nonNull(request.getEmail()))this.emailHelper.sendMail(request.getEmail(),customer.getFullName(),"Tour");

        return TourResponse.builder()
                .reservationsId(tourSaved.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .ticketsId(tourSaved.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourSaved.getId())
                .build();
    }

    @Override
    public TourResponse read(Long id) {
        var tourFromDb=tourRepository.findById(id).orElseThrow(()->new IdNotFoundException("tour"));
        return TourResponse.builder()
                .reservationsId(tourFromDb.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .ticketsId(tourFromDb.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourFromDb.getId())
                .build();
    }

    @Override
    public void delete(Long id) {
        var tourToDelete=tourRepository.findById(id).orElseThrow(()->new IdNotFoundException("tour"));
        tourRepository.delete(tourToDelete);
    }
}
