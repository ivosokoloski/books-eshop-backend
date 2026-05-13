package mk.ukim.finki.wp.eimt_lab.model.dto;


import mk.ukim.finki.wp.eimt_lab.model.domain.User;
import mk.ukim.finki.wp.eimt_lab.model.enums.Role;

public record RegisterUserResponseDto(
    String username,
    String name,
    String surname,
    String email,
    Role role
) {
    public static RegisterUserResponseDto from(User user) {
        return new RegisterUserResponseDto(
            user.getUsername(),
            user.getName(),
            user.getSurname(),
            user.getEmail(),
            user.getRole()
        );
    }
}


