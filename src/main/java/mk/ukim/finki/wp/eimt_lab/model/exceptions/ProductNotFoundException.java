package mk.ukim.finki.wp.eimt_lab.model.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("A product with id %d does not exist.".formatted(id));
    }
}
