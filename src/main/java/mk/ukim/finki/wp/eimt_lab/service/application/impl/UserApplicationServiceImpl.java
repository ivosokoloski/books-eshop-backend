package mk.ukim.finki.wp.eimt_lab.service.application.impl;

import java.util.Optional;
import mk.ukim.finki.wp.eimt_lab.helpers.JwtHelper;
import mk.ukim.finki.wp.eimt_lab.model.domain.User;
import mk.ukim.finki.wp.eimt_lab.model.dto.*;
import mk.ukim.finki.wp.eimt_lab.service.application.UserApplicationService;
import mk.ukim.finki.wp.eimt_lab.service.domain.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto) {
        User user = userService.register(registerUserRequestDto.toUser());
        RegisterUserResponseDto displayUserDto = RegisterUserResponseDto.from(user);
        return Optional.of(displayUserDto);
    }

    @Override
    public Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto) {
        User user = userService.login(loginUserRequestDto.username(), loginUserRequestDto.password());

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginUserResponseDto(token));
    }

    @Override
    public Optional<RegisterUserResponseDto> findByUsername(String username) {
        return userService
            .findByUsername(username)
            .map(RegisterUserResponseDto::from);
    }

    @Override
    public Optional<DisplayUserDto> deleteById(Long id) {
        return userService
                .deleteById(id)
                .map(DisplayUserDto::from);
    }


    @Override
    public Optional<DisplayUserDto> update(Long id, CreateUserDto createUserDto) {
        return userService
                .update(id, createUserDto.toUser())
                .map(DisplayUserDto::from);
    }
}

