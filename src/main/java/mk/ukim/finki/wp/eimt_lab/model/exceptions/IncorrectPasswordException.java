package mk.ukim.finki.wp.eimt_lab.model.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("The password is incorrect.");
    }
}
