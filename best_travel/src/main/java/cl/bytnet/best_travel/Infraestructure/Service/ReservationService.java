package cl.bytnet.best_travel.Infraestructure.Service;

import cl.bytnet.best_travel.API.Model.Request.ReservationRequest;
import cl.bytnet.best_travel.API.Model.Response.HotelResponse;
import cl.bytnet.best_travel.API.Model.Response.ReservationResponse;
import cl.bytnet.best_travel.Domain.Entities.ReservationEntity;
import cl.bytnet.best_travel.Domain.Repositories.JPA.CustomerRepository;
import cl.bytnet.best_travel.Domain.Repositories.JPA.HotelRepository;
import cl.bytnet.best_travel.Domain.Repositories.JPA.ReservationRepository;
import cl.bytnet.best_travel.Infraestructure.AbstractService.IReservationService;
import cl.bytnet.best_travel.Infraestructure.Helper.ApiCurrencyConnectorHelper;
import cl.bytnet.best_travel.Infraestructure.Helper.CustomerHelper;
import cl.bytnet.best_travel.Infraestructure.Helper.EmailHelper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@AllArgsConstructor
@Transactional
@Service

public class ReservationService implements IReservationService {
    private final ReservationRepository repository;
    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final CustomerHelper customerHelper;
    private final ApiCurrencyConnectorHelper currencyConnectorHelper;
    private final EmailHelper emailHelper;
    @Override
    public ReservationResponse create(ReservationRequest request) {
        var hotel=hotelRepository.findById(request.getIdHotel()).orElseThrow();
        var customer=customerRepository.findById(request.getIdClient()).orElseThrow();
        var reservation=ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .dateReservation(LocalDateTime.now())
                .dateEnd(LocalDate.now())
                .dateStart(LocalDate.now())
                .price(hotel.getPrice().add(hotel.getPrice().multiply(BigDecimal.valueOf(0.25))))
                .totalDays(10)
                .build();
        var reservationToPersistence=repository.save(reservation);
        customerHelper.incrase(customer.getDni(),ReservationService.class);
        log.info("Reservation created with id: {}",reservationToPersistence.getId());
        if (Objects.nonNull(request.getEmail()))this.emailHelper.sendMail(request.getEmail(),customer.getFullName(),"Reservation");
        return this.entityToResponse(reservationToPersistence);
    }

    @Override
    public ReservationResponse read(UUID id) {
        var reservation=repository.findById(id).orElseThrow();
        return this.entityToResponse(reservation);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {
        var hotel=hotelRepository.findById(request.getIdHotel()).orElseThrow();
        var client=customerRepository.findById(request.getIdClient()).orElseThrow();
        var reservation=repository.findById(id).orElseThrow();
        reservation.setHotel(hotel);
        reservation.setCustomer(client);
        return this.entityToResponse(reservation);
    }

    @Override
    public void delete(UUID id) {
        var reservation=repository.findById(id).orElseThrow();
        repository.delete(reservation);
        log.info("Reservation deleted with id:{}",reservation.getId());
    }
    private ReservationResponse entityToResponse(ReservationEntity entity){
        var response=new ReservationResponse();
        BeanUtils.copyProperties(entity,response);
        var hotelResponse= new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(),hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }

    @Override
    public BigDecimal findPrice(Long idHotel, Currency currency) {
        var hotel=hotelRepository.findById(idHotel).orElseThrow();
        var priceInDollar= hotel.getPrice().add(hotel.getPrice().multiply(BigDecimal.TEN));
        if (currency.equals(Currency.getInstance("USD"))) return priceInDollar;
        var currencyDto=this.currencyConnectorHelper.getCurrency(currency);
        log.info("API CURRENCY in:{}, response:{}",currencyDto.getDate(),currencyDto.getResult());
        return priceInDollar.multiply(currencyDto.getResult());
    }
}
