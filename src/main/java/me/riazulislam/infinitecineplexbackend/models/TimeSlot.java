package me.riazulislam.infinitecineplexbackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "time_slots")
public class TimeSlot extends BaseModel{
    @Column(nullable = false)
    private String name;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private Duration duration;

    @OneToMany(mappedBy = "timeSlot", orphanRemoval = false)
    private List<DayTimeSlot> dayTimeSlots = new ArrayList<>();
}
