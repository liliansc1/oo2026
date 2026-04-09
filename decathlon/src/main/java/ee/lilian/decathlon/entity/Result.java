package ee.lilian.decathlon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Event event;      // ala nt 100m/LONG_JUMP jne
    private Double result;    // tulemus 12.5/8.0 jne (sek või m)
    private Integer points;   // backend arvutab

    @ManyToOne
    @JoinColumn(name = "person{id}")
    private Person person;
}
