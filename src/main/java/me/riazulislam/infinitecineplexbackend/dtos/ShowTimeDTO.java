package me.riazulislam.infinitecineplexbackend.dtos;

import lombok.*;
import me.riazulislam.infinitecineplexbackend.models.DayTimeSlot;
import me.riazulislam.infinitecineplexbackend.models.Reservation;
import me.riazulislam.infinitecineplexbackend.models.ReservationSeat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowTimeDTO {
    private Long id;
    private MovieDTO movie;
    private String status;
    private DayTimeSlot timeSlot;
    private LocalDate showDate;
    private List<Reservation> reservations;
    private List<ReservationSeat> reservationSeats;
}
