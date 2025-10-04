package me.riazulislam.infinitecineplexbackend.services.impl;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.models.Movie;
import me.riazulislam.infinitecineplexbackend.repositories.MovieRepository;
import me.riazulislam.infinitecineplexbackend.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public Movie createNewMovie(Movie movie) {
        try {
            if (movieRepository.existsMovieByTitle(movie.getTitle())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Movie already exists");
            }

            return movieRepository.save(movie);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
