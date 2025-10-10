package me.riazulislam.infinitecineplexbackend.controllers;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.dtos.CreateReservationDTO;
import me.riazulislam.infinitecineplexbackend.mappers.ReservationMapper;
import me.riazulislam.infinitecineplexbackend.models.Reservation;
import me.riazulislam.infinitecineplexbackend.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @PostMapping
    public ResponseEntity<?> createReservation(@Validated @RequestBody CreateReservationDTO reservationDTO) {
        Reservation createdReservation = reservationService.createNewReservation(reservationMapper.toModel(reservationDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(reservationMapper.toDTO(createdReservation));
    }
}
