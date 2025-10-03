package me.riazulislam.infinitecineplexbackend.controllers;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.dtos.CreateGenreDTO;
import me.riazulislam.infinitecineplexbackend.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

//    @PostMapping
//    public ResponseEntity<?> createMovie() {
//
//    }
//
//    @PostMapping("/genre")
//    public ResponseEntity<?> createGenre(CreateGenreDTO genreDTO) {
//
//    }
}
