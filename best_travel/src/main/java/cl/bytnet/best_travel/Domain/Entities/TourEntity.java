package cl.bytnet.best_travel.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity(name = "tour")
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private CustomerEntity customer;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "tour",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<TicketEntity>tickets;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "tour",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<ReservationEntity>reservations;
//MAPEO INVERSO, PERSISTENCIA SOLO LLAMANDO LOS METODOS
//    public void addTicket(TicketEntity ticket){
//        if(Objects.isNull(this.tickets))this.tickets=new HashSet<>();
//        this.tickets.add(ticket);
//    }
//    public void removeTicket2(UUID id){
//        if(Objects.isNull(this.tickets))this.tickets=new HashSet<>();
//        this.tickets.removeIf(ticket -> ticket.getId().equals(id));
//    }
//    public void updateTicket(){
//        this.tickets.forEach(ticket -> ticket.setTour(this));
//    }
//
//    public void addReservation(ReservationEntity reservation){
//        if(Objects.isNull(this.reservations))this.reservations=new HashSet<>();
//        this.reservations.add(reservation);
//    }
//    public void removeReservation(UUID id){
//        if(Objects.isNull(this.reservations))this.reservations=new HashSet<>();
//        this.reservations.removeIf(reservation -> reservation.getId().equals(id));
//    }
//    public void updateReservation(){
//        this.reservations.forEach(reservation -> reservation.setTour(this));
//    }

// UPDATE FK AUTOMATICAMENTE

    @PrePersist
    @PreRemove
    public void updateFk(){
        this.reservations.forEach(reservation -> reservation.setTour(this));
        this.tickets.forEach(ticket -> ticket.setTour(this));
    }

    public void removeTicket(UUID idTicket){
        this.tickets.forEach(t->{
            if (t.getId().equals(idTicket)){
                t.setTour(null);
            }
        });
    }
    public void addTicket(TicketEntity ticket){
        if (Objects.isNull(tickets))tickets=new HashSet<>();
        tickets.add(ticket);
        this.tickets.forEach(t -> t.setTour(this));
    }

    public void removeReservation(UUID idReservation){
        reservations.forEach(r->{
            if (r.getId().equals(idReservation)){
                r.setTour(null);
            }
        });
    }
    public void addReservation(ReservationEntity reservation){
        if (Objects.isNull(reservations))reservations=new HashSet<>();
        reservations.add(reservation);
        reservations.forEach(r-> r.setTour(this));

    }

}
