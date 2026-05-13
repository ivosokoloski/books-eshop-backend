package mk.ukim.finki.wp.eimt_lab.model.exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String productName) {
        super("Not enough stock for %s.".formatted(productName));
    }
}
