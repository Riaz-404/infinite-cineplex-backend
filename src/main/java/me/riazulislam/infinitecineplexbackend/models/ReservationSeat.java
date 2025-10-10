//package me.riazulislam.infinitecineplexbackend.models;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table(
//        name = "reservation_seats",
//        uniqueConstraints = @UniqueConstraint(name = "uk_show_seat", columnNames = {"show_id", "seat_id"})
//)
//public class ReservationSeat extends BaseModel {
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "reservation_id", nullable = false)
//    @JsonManagedReference
//    private Reservation reservation;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "show_id", nullable = false)
//    @JsonManagedReference
//    private ShowTime showTime;
//
//    @Column(name = "seat_id", nullable = false)
//    private String seatId;
//}
