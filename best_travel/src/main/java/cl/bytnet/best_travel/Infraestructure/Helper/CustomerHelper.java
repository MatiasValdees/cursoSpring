package cl.bytnet.best_travel.Infraestructure.Helper;

import cl.bytnet.best_travel.Domain.Repositories.JPA.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
@Component
public class CustomerHelper {
    private CustomerRepository customerRepository;
    public void incrase(String customerId,Class<?>type){ //cualquier tipo de clase asi evitamos crear un Enum con los nombres de las clases
        var customer=customerRepository.findById(customerId).orElseThrow();
        switch (type.getSimpleName()){
            case "TourService"->customer.setTotalTours(customer.getTotalTours()+1);
            case "TicketService"->customer.setTotalFlights(customer.getTotalFlights()+1);
            case "ReservationService"->customer.setTotalLodgings(customer.getTotalLodgings()+1);
        }
        customerRepository.save(customer);
    }
}
