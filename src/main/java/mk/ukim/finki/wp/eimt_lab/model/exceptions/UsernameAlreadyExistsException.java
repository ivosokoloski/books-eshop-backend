package mk.ukim.finki.wp.eimt_lab.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("User with username '%s' already exists.".formatted(username));
    }
}
