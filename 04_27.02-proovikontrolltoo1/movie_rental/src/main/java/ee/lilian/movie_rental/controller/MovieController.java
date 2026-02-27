package ee.lilian.movie_rental.controller;


import ee.lilian.movie_rental.dto.MovieSaveDto;
import ee.lilian.movie_rental.entity.Movie;
import ee.lilian.movie_rental.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private MovieRepository movieRepository;

    @PostMapping("movies") //lisamine
    public Movie saveMovie(@RequestBody MovieSaveDto movieSaveDto){
        Movie movie = new Movie();
        movie.setTitle(movieSaveDto.title());
        movie.setType(movieSaveDto.type());
        movie.setDays(0);
        return movieRepository.save(movie);
    }

    @DeleteMapping("movies/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieRepository.deleteById(id); //kustutan
    }

    @PutMapping("movies")//muutmine
    public List<Movie> editMovie(@RequestBody Movie movie){
        if (movie.getId()==null){
            throw new RuntimeException("Cannot edit without ID");
        }
        if (!movieRepository.existsById(movie.getId())){
            throw new RuntimeException("Movie ID does not exist");
        }
        movieRepository.save(movie);//siin salvestab
        return movieRepository.findAll();//siin on uuenenud seis
    }
}

