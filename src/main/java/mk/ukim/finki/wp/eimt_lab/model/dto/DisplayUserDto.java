package mk.ukim.finki.wp.eimt_lab.model.dto;

import mk.ukim.finki.wp.eimt_lab.model.domain.User;

import java.util.List;

public record DisplayUserDto(
        Long id,
        String name,
        String surname,
        String email

) {
    public static DisplayUserDto from(User user){
        return  new DisplayUserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail()
        );
    }
    public static List<DisplayUserDto> from(List<User> countries){
        return countries.stream().map(DisplayUserDto::from).toList();
    }

}
