package me.riazulislam.infinitecineplexbackend.services.impl;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.models.ShowTime;
import me.riazulislam.infinitecineplexbackend.repositories.ShowTimeRepository;
import me.riazulislam.infinitecineplexbackend.services.ShowTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {
    private final ShowTimeRepository showTimeRepository;

    @Override
    public ShowTime createNewShowTime(ShowTime showTime) {
        try {
            if (showTimeRepository.existsShowTimeByMovieAndDayTimeSlotAndShowDate(showTime.getMovie(), showTime.getDayTimeSlot(), showTime.getShowDate())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Movie with this slot is already exist");
            }

            return showTimeRepository.save(showTime);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
