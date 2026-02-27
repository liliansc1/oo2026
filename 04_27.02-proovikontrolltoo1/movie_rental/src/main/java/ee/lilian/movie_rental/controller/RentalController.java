package ee.lilian.movie_rental.controller;


import ee.lilian.movie_rental.dto.MovieRentalDto;
import ee.lilian.movie_rental.entity.Movie;
import ee.lilian.movie_rental.entity.Rental;
import ee.lilian.movie_rental.repository.MovieRepository;
import ee.lilian.movie_rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalRepository rentalRepository;
    private final MovieRepository movieRepository;
    private double premiumPrice = 4;
    private double basicPrice = 3;

    @GetMapping("rentals")
    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    // DTO ---> mis film (ID), mitmeks päevaks võtan
    @PostMapping("start-rental")
    public Rental startRental(@RequestBody List<MovieRentalDto> movieRentalDtos) {
        // CascadeType.ALL ---> see asendaks kaks korda salvestamist
        // saaksin saavutada olukorra, kus Rentalit salvestades salvestatakse ka Filmid ära

        // Funktsioon läheb liiga pikaks ----> peaks tõstma Service-sse.
        // Integratsioonitest -> Controller (kas töötab, okei staatuskood, tagastab korrektselt)
        // Unit test -> Service (kas algoritmid töötavad, kas arvutab korrektselt)

        // filmRentalDtos.forEach((filmRentalDto: FilmRentalDto) => MIDA TEEN)
        Rental rental = new Rental(); // {id: null, initialFee: null, lateFee: null}
        Rental dbRental = rentalRepository.save(rental); // {id: 2, initialFee: null, lateFee: null}

        double sum = 0;
        // mis tüüp       muutuja          mida läbi käin
        for (MovieRentalDto movieRentalDto : movieRentalDtos) {
            Movie dbMovie_rental = movieRepository.findById(movieRentalDto.movieId()).orElseThrow();
            dbMovie_rental.setRental(dbRental);
            dbMovie_rental.setDays(movieRentalDto.days());
            switch (dbMovie_rental.getType()) {
                case NEW -> sum += premiumPrice * movieRentalDto.days();
                case REGULAR -> {
                    if (movieRentalDto.days() <= 3) {
                        sum += basicPrice;
                    } else {
                        sum += basicPrice + basicPrice * (movieRentalDto.days() - 3);
                    }
                }
                case OLD -> {
                    if (movieRentalDto.days() <= 5) {
                        sum += basicPrice;
                    } else {
                        sum += basicPrice + basicPrice * (movieRentalDto.days() - 5);
                    }
                }
            }
            movieRepository.save(dbMovie_rental);
        }

        dbRental.setInitialFee(sum); // {id: 2, initialFee: 123.0, lateFee: null}
        return rentalRepository.save(dbRental);
    }

    // DTO ---> mis film (ID), mitu päeva tegelikult rendis oli
    @PostMapping("end-rental")
    public double endRental(@RequestBody List<MovieRentalDto> movieRentalDtos) {

        double sum = 0;
        for (MovieRentalDto movieRentalDto : movieRentalDtos) {
            Movie dbMovie_rental = movieRepository.findById(movieRentalDto.movieId()).orElseThrow();
            Rental rental = dbMovie_rental.getRental();
            // switch case --> filmi_summa arvutamine + summale juurde liitmine
            rental.setLateFee(rental.getLateFee() + FILMI_SUMMA); // <-- võib olla switchi sees
            rentalRepository.save(rental);

            dbMovie_rental.setRental(null);
            dbMovie_rental.setDays(0);
            movieRepository.save(dbMovie_rental);
        }
        return sum; // maksmisele lähev summa (võib tulla erinevatest rentalitest)
    }
}