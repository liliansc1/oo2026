package ee.lilian.movie_rental.controller;


import ee.lilian.movie_rental.dto.MovieSaveDto;
import ee.lilian.movie_rental.entity.Movie;
import ee.lilian.movie_rental.entity.MovieType;
import ee.lilian.movie_rental.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;

    @PostMapping("films")
    public Movie saveMovie(@RequestBody MovieSaveDto movieSaveDto){
        Movie movie = new Movie();
        movie.setTitle(movieSaveDto.title());
        movie.setType(movieSaveDto.type());
        movie.setDays(0);
        return movieRepository.save(movie);
    }

    @DeleteMapping("movies/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieRepository.deleteById(id);
    }

    // PUT - kogu entity muutmise võimekus
    // PATCH - booking võetud. tellimus makstud. kogus ühe võrra vähendatud.
    @PatchMapping("movies/type")
    public Movie changeMovieType(@RequestParam Long id, @RequestParam MovieType filmType){
        Movie movie = movieRepository.findById(id).orElseThrow();
        movie.setType(filmType);
        return movieRepository.save(movie);
    }

    @GetMapping("movies")
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    @GetMapping("movies/available")
    public List<Movie> findAllAvailable(){
        return movieRepository.findByDays(0);
    }
}