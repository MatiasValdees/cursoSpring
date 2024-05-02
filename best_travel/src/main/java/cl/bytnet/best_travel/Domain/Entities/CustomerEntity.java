package cl.bytnet.best_travel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity(name = "customer")
public class CustomerEntity implements Serializable {
    @Id
    @Column(length = 12)
    private String dni;

    @Column(name = "full_name",length = 50)
    private String fullName;
    @Column(name = "credit_card",length = 20)
    private String creditCard;
    @Column(name = "total_flights")
    private Integer totalFlights;
    @Column(name = "total_lodgings")
    private Integer totalLodgings;
    @Column(name = "total_tours")
    private Integer totalTours;
    @Column(name = "phone_number",length = 12)
    private String phoneNumber;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<ReservationEntity>reservations;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<TicketEntity> tickets;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "customer",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private Set<TourEntity>tours;
}
