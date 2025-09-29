package me.riazulislam.infinitecineplexbackend.models;

import jakarta.persistence.*;
import lombok.*;
import me.riazulislam.infinitecineplexbackend.enums.ShowStatusEnum;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "show_times")
public class ShowTime extends BaseModel {
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ShowStatusEnum showStatus = ShowStatusEnum.Scheduled;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private DayTimeSlot dayTimeSlot;

    @Column(name = "show_date", nullable = false)
    private Instant showDate;

    @OneToMany(mappedBy = "showTime")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "showTime")
    private List<ReservationSeat> reservationSeats = new ArrayList<>();
}
