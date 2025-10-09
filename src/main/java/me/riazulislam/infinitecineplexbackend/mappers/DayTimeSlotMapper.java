package me.riazulislam.infinitecineplexbackend.mappers;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.dtos.CreateDayTimeSlotDTO;
import me.riazulislam.infinitecineplexbackend.dtos.CreateTimeSlotDTO;
import me.riazulislam.infinitecineplexbackend.models.DayTimeSlot;
import me.riazulislam.infinitecineplexbackend.models.TimeSlot;
import me.riazulislam.infinitecineplexbackend.repositories.TimeSlotRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.management.RuntimeErrorException;

@Component
@AllArgsConstructor
public class DayTimeSlotMapper {
    private final TimeSlotRepository timeSlotRepository;

    public DayTimeSlot toEntity(CreateDayTimeSlotDTO dayTimeSlotDTO) {
        TimeSlot timeSlot = timeSlotRepository.findById(dayTimeSlotDTO.getTime_slot()).orElseThrow(()->new RuntimeException("Time slot not found"));
        return DayTimeSlot.builder()
                .day(dayTimeSlotDTO.getDay())
                .timeSlot(timeSlot)
                .build();
    }
}
