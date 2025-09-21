package me.riazulislam.infinitecineplexbackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie extends BaseModel {
    @Column(nullable = false)
    private String title;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    private String description;

    @Column(nullable = false)
    private Duration duration;

    private Double rating;

    @Column(name = "poster_image")
    private String posterImage;
}
