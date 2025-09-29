package me.riazulislam.infinitecineplexbackend.services;

import me.riazulislam.infinitecineplexbackend.dtos.CreateUserDTO;
import me.riazulislam.infinitecineplexbackend.dtos.UserDTO;

public interface UserService {
    UserDTO createNewUser(CreateUserDTO user);
}
