package me.riazulislam.infinitecineplexbackend.services.impl;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.enums.SeatStatusEnum;
import me.riazulislam.infinitecineplexbackend.models.Reservation;
import me.riazulislam.infinitecineplexbackend.models.Seat;
import me.riazulislam.infinitecineplexbackend.repositories.ReservationRepository;
import me.riazulislam.infinitecineplexbackend.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    @Override
    public Reservation createNewReservation(Reservation reservation) {
        try {
            for(Seat seat: reservation.getSeats()) {
                if (seat.getStatus() != SeatStatusEnum.Active) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Seat " + seat.getSeat() + " of this show can not be booked");
                }

                boolean alreadyBooked = reservationRepository.existsByShowTime_IdAndSeats_Id(reservation.getShowTime().getId(), seat.getId());

                if (alreadyBooked) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Seat " + seat.getSeat() + " of this show is already booked");
                }
            }

            return reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
