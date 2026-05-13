package mk.ukim.finki.wp.eimt_lab.model.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException {
    public ShoppingCartNotFoundException(Long id) {
        super("A shopping cart with id %d does not exist.".formatted(id));
    }
}
