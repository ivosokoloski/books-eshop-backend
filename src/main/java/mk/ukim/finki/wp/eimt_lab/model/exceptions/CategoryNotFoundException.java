package mk.ukim.finki.wp.eimt_lab.model.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("A category with id %d does not exist.".formatted(id));
    }
}
