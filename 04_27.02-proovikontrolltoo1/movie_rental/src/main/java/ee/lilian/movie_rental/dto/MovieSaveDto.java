package ee.lilian.movie_rental.dto;

import ee.lilian.movie_rental.entity.MovieType;

public record MovieSaveDto(
        String title,
        MovieType type
) {
}
