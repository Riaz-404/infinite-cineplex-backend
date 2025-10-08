package me.riazulislam.infinitecineplexbackend.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String name;
    private String phoneNumber;
    private String role;
}
