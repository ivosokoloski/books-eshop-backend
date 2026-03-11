package mk.ukim.finki.wp.eimt_lab.model.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long id) {
        super("A author with id %d does not exist.".formatted(id));
    }

}
