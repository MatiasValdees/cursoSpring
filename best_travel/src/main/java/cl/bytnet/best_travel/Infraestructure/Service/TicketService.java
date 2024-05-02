package cl.bytnet.best_travel.Infraestructure.Service;

import cl.bytnet.best_travel.API.Model.Request.TicketRequest;
import cl.bytnet.best_travel.API.Model.Response.FlyResponse;
import cl.bytnet.best_travel.API.Model.Response.TicketResponse;
import cl.bytnet.best_travel.Domain.Entities.TicketEntity;
import cl.bytnet.best_travel.Domain.Repositories.JPA.CustomerRepository;
import cl.bytnet.best_travel.Domain.Repositories.JPA.FlyRepository;
import cl.bytnet.best_travel.Domain.Repositories.JPA.TicketRepository;
import cl.bytnet.best_travel.Infraestructure.AbstractService.ITicketService;
import cl.bytnet.best_travel.Infraestructure.Helper.CustomerHelper;
import cl.bytnet.best_travel.Infraestructure.Helper.EmailHelper;
import cl.bytnet.best_travel.Util.BestTravelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor

public class TicketService implements ITicketService {
    //REPOSITORIOS PARA INYECTAR FLY Y CUSTOMER A TICKET
    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final CustomerHelper customerHelper;
    private final EmailHelper emailHelper;
    private final BigDecimal charger_price_percentage=BigDecimal.valueOf(0.25);

    @Override
    public TicketResponse create(TicketRequest request) {
            var fly=flyRepository.findById(request.getIdFly()).orElseThrow();
            var customer=customerRepository.findById(request.getIdClient()).orElseThrow();
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
            customerHelper.incrase(customer.getDni(),TicketService.class);
        if (Objects.nonNull(request.getEmail()))this.emailHelper.sendMail(request.getEmail(),customer.getFullName(),"Ticket");

        return entityToResponse(ticketPersisted);
    }


    @Override
    public TicketResponse read(UUID id) {

        var ticketFromDB=ticketRepository.findById(id).orElseThrow();
        return entityToResponse(ticketFromDB);
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID id) {
        var fly=flyRepository.findById(request.getIdFly()).orElseThrow();
        var ticketToUpdate= ticketRepository.findById(id).orElseThrow();
        ticketToUpdate.setFly(fly);
        ticketToUpdate.setPrice(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage)));
        ticketToUpdate.setDepartureDate(BestTravelUtil.getRandomSoon());
        ticketToUpdate.setArrivalDate(BestTravelUtil.getRandomLatter());
        //Si tiene un un id lo actualiza si no lo tiene lo inserta
        var ticketUpdated=this.ticketRepository.save(ticketToUpdate);
        log.info("Ticket updated with id: {}",ticketUpdated.getId());
        return this.entityToResponse(ticketUpdated);
    }

    @Override
    public void delete(UUID id) {
        var ticket=ticketRepository.findById(id).orElseThrow();
        ticketRepository.delete(ticket);
        log.info("Ticket deleted with id: {}",ticket.getId());
    }
    //Mapeo entidades-Request
    private TicketResponse entityToResponse(TicketEntity entity){
        var response=new TicketResponse();
        BeanUtils.copyProperties(entity,response);
        var flyResponse=new FlyResponse();
        BeanUtils.copyProperties(entity.getFly(),flyResponse);
        response.setFly(flyResponse);
        return response;
    }

    @Override
    public BigDecimal findPrice(Long idFly) {
        var fly=flyRepository.findById(idFly).orElseThrow();
        return fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage));
    }
}
