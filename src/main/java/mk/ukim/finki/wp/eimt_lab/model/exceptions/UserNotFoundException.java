package mk.ukim.finki.wp.eimt_lab.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User with username '%s' does not exist.".formatted(username));
    }
}
