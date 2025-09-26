package me.riazulislam.infinitecineplexbackend.models;

import jakarta.persistence.*;
import lombok.*;
import me.riazulislam.infinitecineplexbackend.enums.DaysEnum;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "day_time_slots")
public class DayTimeSlot extends BaseModel{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DaysEnum day;

    @ManyToOne
    @JoinColumn(name = "time_slot_id", nullable = false)
    private TimeSlot timeSlot;

    @OneToMany(mappedBy = "dayTimeSlot")
    private List<ShowTime> showTimes = new ArrayList<>();
}
