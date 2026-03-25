package mk.ukim.finki.wp.eimt_lab.event;

import mk.ukim.finki.wp.eimt_lab.model.domain.Book;

public record BookRentedEvent(java.util.Optional<Book> book) {
}