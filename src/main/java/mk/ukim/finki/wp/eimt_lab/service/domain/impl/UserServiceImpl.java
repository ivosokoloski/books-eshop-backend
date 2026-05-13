package mk.ukim.finki.wp.eimt_lab.service.domain.impl;

import mk.ukim.finki.wp.eimt_lab.model.domain.User;
import mk.ukim.finki.wp.eimt_lab.model.exceptions.IncorrectPasswordException;
import mk.ukim.finki.wp.eimt_lab.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wp.eimt_lab.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.eimt_lab.repository.UserRepository;
import mk.ukim.finki.wp.eimt_lab.service.domain.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new UsernameAlreadyExistsException(user.getUsername());
        return userRepository.save(new User(
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getUsername(),
                passwordEncoder.encode(user.getPassword())
        ));
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new IncorrectPasswordException();
        return user;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }


}
