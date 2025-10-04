package me.riazulislam.infinitecineplexbackend.mappers;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.dtos.CreateMovieDTO;
import me.riazulislam.infinitecineplexbackend.models.Genre;
import me.riazulislam.infinitecineplexbackend.models.Movie;
import me.riazulislam.infinitecineplexbackend.services.GenreService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class MovieMapper {
    private final GenreService genreService;

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

//    public MovieDTO toDTO() {
//        return null;
//    }
}
