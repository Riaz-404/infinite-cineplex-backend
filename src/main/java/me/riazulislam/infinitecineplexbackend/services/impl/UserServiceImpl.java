package me.riazulislam.infinitecineplexbackend.services.impl;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.dtos.CreateUserDTO;
import me.riazulislam.infinitecineplexbackend.dtos.UserDTO;
import me.riazulislam.infinitecineplexbackend.enums.RoleEnum;
import me.riazulislam.infinitecineplexbackend.models.User;
import me.riazulislam.infinitecineplexbackend.repositories.UserRepository;
import me.riazulislam.infinitecineplexbackend.services.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO createNewUser(CreateUserDTO user) {
        // Normalize email early to avoid case or surrounding whitespace producing
        // duplicate entries (e.g., "User@Example.com" vs "user@example.com").
        String normalizedEmail = user.getEmail() == null ? null : user.getEmail().trim().toLowerCase();

        if (normalizedEmail == null || normalizedEmail.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }

        // Basic uniqueness check to provide fast feedback; DB-level unique constraint
        // is the ultimate guard against races.
        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }

        String encoded = passwordEncoder.encode(user.getPassword());

        User newUser = User.builder()
                .email(normalizedEmail)
                .password(encoded)
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .role(RoleEnum.USER)
                .build();

        User createdUser;
        try {
            createdUser = userRepository.save(newUser);
        } catch (DataIntegrityViolationException ex) {
            // Handle race where two requests pass the existsByEmail check simultaneously
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }

        UserDTO createdUserDTO = UserDTO.builder()
                .email(createdUser.getEmail())
                .name(createdUser.getName())
                .phoneNumber(createdUser.getPhoneNumber())
                .role(createdUser.getRole() == null ? null : createdUser.getRole().name())
                .build();

        return createdUserDTO;
    }
}
