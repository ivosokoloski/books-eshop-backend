package mk.ukim.finki.wp.eimt_lab.service.domain;

import mk.ukim.finki.wp.eimt_lab.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findByUsername(String username);
}
