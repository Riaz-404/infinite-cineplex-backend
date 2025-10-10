package me.riazulislam.infinitecineplexbackend.dtos;

import lombok.*;
import me.riazulislam.infinitecineplexbackend.models.User;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private ShowTimeDTO show_time;
    private User user;
    private List<SeatDTO> reservation_seats;
}
