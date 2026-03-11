package mk.ukim.finki.wp.eimt_lab.service.domain;

import mk.ukim.finki.wp.eimt_lab.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService  {
    Optional<Book> findById(Long id);

    List<Book> findAll();

    Book create(Book book);

    Optional<Book> update(Long id, Book book);

    Optional<Book> deleteById(Long id);

}
