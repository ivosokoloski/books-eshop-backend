package mk.ukim.finki.wp.eimt_lab.service.domain.impl;

import mk.ukim.finki.wp.eimt_lab.model.domain.User;
import mk.ukim.finki.wp.eimt_lab.repository.UserRepository;
import mk.ukim.finki.wp.eimt_lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByName(username);
    }
}
