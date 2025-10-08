package me.riazulislam.infinitecineplexbackend.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowTimeDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double price;
}
