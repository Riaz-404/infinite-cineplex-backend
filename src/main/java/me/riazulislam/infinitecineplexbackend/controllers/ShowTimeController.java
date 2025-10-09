package me.riazulislam.infinitecineplexbackend.controllers;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.dtos.CreateShowTimeDTO;
import me.riazulislam.infinitecineplexbackend.mappers.ShowTimeMapper;
import me.riazulislam.infinitecineplexbackend.models.ShowTime;
import me.riazulislam.infinitecineplexbackend.services.ShowTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/show-times")
@AllArgsConstructor
public class ShowTimeController {
    private final ShowTimeService showTimeService;
    private final ShowTimeMapper showTimeMapper;

    @PostMapping
    public ResponseEntity<?> createNewShowTime(@Validated @RequestBody CreateShowTimeDTO showTimeDTO) {
        ShowTime createdNewShowTime = showTimeService.createNewShowTime(showTimeMapper.toModel(showTimeDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(showTimeMapper.toDTO(createdNewShowTime));
    }
}
