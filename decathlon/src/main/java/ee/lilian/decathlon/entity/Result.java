package ee.lilian.decathlon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Event event;      // ala
    private Double result;    // tulemus
    private Integer points;   // punktid

    @ManyToOne
    @JoinColumn(name = "person{id}")
    @JsonBackReference
    private Person person;
}