package mk.ukim.finki.wp.eimt_lab.model.dto;

import mk.ukim.finki.wp.eimt_lab.model.domain.Country;
import mk.ukim.finki.wp.eimt_lab.model.domain.User;



public record CreateUserDto(
        String name,
        String surname,
        String email

) {
    public User toUser(){
        return new User(name,surname,email);
    }
}
