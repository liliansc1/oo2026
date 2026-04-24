package ee.lilian.decathlon.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String nationality;
    private Integer totalPoints;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Result> results;
}
