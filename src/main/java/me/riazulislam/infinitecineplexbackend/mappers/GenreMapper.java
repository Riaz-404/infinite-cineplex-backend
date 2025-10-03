package me.riazulislam.infinitecineplexbackend.mappers;

import me.riazulislam.infinitecineplexbackend.dtos.CreateGenreDTO;
import me.riazulislam.infinitecineplexbackend.models.Genre;

public class GenreMapper {
    public static Genre toEntity(CreateGenreDTO genreDTO) {
        return Genre.builder()
                .name(genreDTO.getName())
                .build();
    }
}
