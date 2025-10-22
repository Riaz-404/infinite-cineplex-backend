package me.riazulislam.infinitecineplexbackend.services.impl;

import lombok.AllArgsConstructor;
import me.riazulislam.infinitecineplexbackend.dtos.LoginRequestDTO;
import me.riazulislam.infinitecineplexbackend.dtos.LoginResponseDTO;
import me.riazulislam.infinitecineplexbackend.models.Jwt;
import me.riazulislam.infinitecineplexbackend.models.User;
import me.riazulislam.infinitecineplexbackend.repositories.UserRepository;
import me.riazulislam.infinitecineplexbackend.services.AuthService;
import me.riazulislam.infinitecineplexbackend.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

        User user = userRepository.findByEmail(loginRequestDTO.getEmail());
        Jwt accessToken = jwtUtils.generateAccessToken(user);
        Jwt refreshToken = jwtUtils.generateRefreshToken(user);

        return new LoginResponseDTO(accessToken, refreshToken);
    }

    @Override
    public Jwt refreshAccessToken(String refreshToken) {
        Jwt jwt = jwtUtils.parseToken(refreshToken);
        if (jwt == null || jwt.isExpired()) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        User user = userRepository.findById(jwt.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return jwtUtils.generateAccessToken(user);
    }
}
