package me.riazulislam.infinitecineplexbackend.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import me.riazulislam.infinitecineplexbackend.models.Genre;
import me.riazulislam.infinitecineplexbackend.models.ShowTime;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieDTO {
    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Release year is required")
    private Integer releaseYear;

    private String description;

    @NotNull(message = "Duration is required")
    private Duration duration;

    private Double rating;

    @NotNull(message = "Movie poster image is required")
    private String posterImage;

    private List<Long> genres;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "movie_genres",
//            joinColumns = @JoinColumn(name = "movie_id"),
//            inverseJoinColumns = @JoinColumn(name = "genre_id")
//    )
//    private List<Genre> genres = new ArrayList<>();
//
//    @OneToMany(mappedBy = "movie")
//    private List<ShowTime> showTimes = new ArrayList<>();
}
