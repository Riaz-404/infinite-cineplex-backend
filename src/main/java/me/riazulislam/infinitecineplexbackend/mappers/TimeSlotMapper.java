package me.riazulislam.infinitecineplexbackend.mappers;

import me.riazulislam.infinitecineplexbackend.dtos.CreateGenreDTO;
import me.riazulislam.infinitecineplexbackend.dtos.CreateTimeSlotDTO;
import me.riazulislam.infinitecineplexbackend.dtos.GenreDTO;
import me.riazulislam.infinitecineplexbackend.models.Genre;
import me.riazulislam.infinitecineplexbackend.models.TimeSlot;
import org.springframework.stereotype.Component;

@Component
public class TimeSlotMapper {
    public TimeSlot toEntity(CreateTimeSlotDTO timeSlotDTO) {
        return TimeSlot.builder()
                .name(timeSlotDTO.getName())
                .startTime(timeSlotDTO.getStart_time())
                .duration(timeSlotDTO.getDuration())
                .build();
    }
}
