package ee.lilian.filmid.controller;

import ee.lilian.filmid.entity.Movie;
import ee.lilian.filmid.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    //localhost:8080/movies
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("movies")
    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    @DeleteMapping("movies/{id}")
    public List<Movie> deleteMovie(@PathVariable Long id){
        movieRepository.deleteById(id); //kustutan
        return movieRepository.findAll();//uuenenud seis
    }

    @PostMapping("movies")
    public List<Movie> addMovie(@RequestBody Movie movie){
        movieRepository.save(movie);//siin salvestab
        return movieRepository.findAll();//siin on uuenenud seis
    }
}
