package ee.lilian.movie_rental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private MovieType type;//aitab valtida valesid vaartuseid
    private int days;//mitu päeva oli renditud, kui 0, siis available

    @ManyToOne
    private Rental rental;
}
