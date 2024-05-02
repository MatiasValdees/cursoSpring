package cl.bytnet.best_travel.Domain.Entities;

import cl.bytnet.best_travel.Util.Enum.Aeroline;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity(name = "fly")
public class FlyEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //BigSerial
    @Column(name = "origin_lat")
    private Double originLat; //Decimal
    @Column(name = "origin_lng")
    private Double originLng;
    @Column(name = "destiny_lat")
    private Double destinyLat;
    @Column(name = "destiny_lng")
    private Double destinyLng;
    private BigDecimal price;
    @Column(name = "origin_name",length = 20)
    private String originName;
    @Column(name = "destiny_name",length = 20)
    private String destinyName;
    @Enumerated(EnumType.STRING)
    @Column(name = "aero_line",length = 20)
    private Aeroline aeroLine;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(//1 Vuelo puede estar en muchos tickets esto crea una lista con todos los ticket asociados.
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "fly",
            orphanRemoval = true
    )
    private Set<TicketEntity> tickets;

}
