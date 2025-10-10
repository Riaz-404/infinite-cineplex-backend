package me.riazulislam.infinitecineplexbackend.mappers;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.dtos.CreateShowTimeDTO;
import me.riazulislam.infinitecineplexbackend.dtos.GenreDTO;
import me.riazulislam.infinitecineplexbackend.dtos.MovieDTO;
import me.riazulislam.infinitecineplexbackend.dtos.ShowTimeDTO;
import me.riazulislam.infinitecineplexbackend.models.DayTimeSlot;
import me.riazulislam.infinitecineplexbackend.models.Genre;
import me.riazulislam.infinitecineplexbackend.models.Movie;
import me.riazulislam.infinitecineplexbackend.models.ShowTime;
import me.riazulislam.infinitecineplexbackend.repositories.DayTimeSlotRepository;
import me.riazulislam.infinitecineplexbackend.repositories.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ShowTimeMapper {
    private final MovieRepository movieRepository;
    private final DayTimeSlotRepository dayTimeSlotRepository;
    private final MovieMapper movieMapper;

    public ShowTime toModel(CreateShowTimeDTO showTimeDTO) {
        Movie movie = movieRepository.findById(showTimeDTO.getMovie_id()).orElseThrow(() -> new RuntimeException("Movie not found"));

        DayTimeSlot dayTimeSlot = dayTimeSlotRepository.findById(showTimeDTO.getDay_time_slot()).orElseThrow(() -> new RuntimeException("Day time slot not found"));

        return ShowTime.builder()
                .showStatus(showTimeDTO.getStatus())
                .movie(movie)
                .dayTimeSlot(dayTimeSlot)
                .showDate(showTimeDTO.getShow_date())
                .build();
    }

    public ShowTimeDTO toDTO(ShowTime showTime) {

        return ShowTimeDTO.builder()
                .id(showTime.getId())
                .movie(movieMapper.toDTO(showTime.getMovie()))
                .status(String.valueOf(showTime.getShowStatus()))
                .timeSlot(showTime.getDayTimeSlot())
                .showDate(showTime.getShowDate())
                .reservations(showTime.getReservations())
                .build();
    }
}
