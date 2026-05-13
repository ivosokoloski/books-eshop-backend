package mk.ukim.finki.wp.eimt_lab.service.application;

import mk.ukim.finki.wp.eimt_lab.model.dto.*;

import java.util.Optional;

public interface UserApplicationService {
    Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto);

    Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto);

    Optional<RegisterUserResponseDto> findByUsername(String username);
    Optional<DisplayUserDto> deleteById(Long id);

    Optional<DisplayUserDto> update(Long id, CreateUserDto createUserDto);
}
