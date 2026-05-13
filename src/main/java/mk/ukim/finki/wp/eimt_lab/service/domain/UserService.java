package mk.ukim.finki.wp.eimt_lab.service.domain;

import mk.ukim.finki.wp.eimt_lab.model.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findByUsername(String username);
    User register(User user);

    User login(String username, String password);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
