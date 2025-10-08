package me.riazulislam.infinitecineplexbackend.mappers;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.dtos.CreateMovieDTO;
import me.riazulislam.infinitecineplexbackend.dtos.GenreDTO;
import me.riazulislam.infinitecineplexbackend.dtos.MovieDTO;
import me.riazulislam.infinitecineplexbackend.models.Genre;
import me.riazulislam.infinitecineplexbackend.models.Movie;
import me.riazulislam.infinitecineplexbackend.services.GenreService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class MovieMapper {
    private final GenreService genreService;
    private final GenreMapper genreMapper;

    public Movie toModel(CreateMovieDTO dto) {
        if (dto == null) {
            return null;
        }
        List<Long> genreIds = dto.getGenres();

        List<Genre> genres = new ArrayList<>();

        for  (Long genreId : genreIds) {
            Genre genre = genreService.getGenreById(genreId);
            genres.add(genre);
        }

        return Movie.builder()
                .title(dto.getTitle())
                .releaseYear(dto.getReleaseYear())
                .description(dto.getDescription())
                .rating(dto.getRating())
                .duration(dto.getDuration())
                .posterImage(dto.getPosterImage())
                .genres(genres)
                .build();
    }

    public MovieDTO toDTO(Movie movie) {
        List<GenreDTO> genreDTOList = new ArrayList<>();
        List<Genre> genresList = movie.getGenres();

        for(Genre genre: genresList) {
            genreDTOList.add(genreMapper.toDTO(genre));
        }

        return MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .releaseYear(movie.getReleaseYear())
                .description(movie.getDescription())
                .duration(movie.getDuration())
                .rating(movie.getRating())
                .posterImage(movie.getPosterImage())
                .genres(genreDTOList)
                .build();
    }
}
