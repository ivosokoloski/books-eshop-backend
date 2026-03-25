package mk.ukim.finki.wp.eimt_lab.service.domain;

import mk.ukim.finki.wp.eimt_lab.model.domain.Book;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookCategory;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService  {
    Optional<Book> findById(Long id);

    List<Book> findAll();

    Book create(Book book);

    Optional<Book> update(Long id, Book book);

    Optional<Book> deleteById(Long id);
    Page<Book> findAll(BookCategory category, BookState state, Long authorId, Boolean available, Pageable pageable);
    Optional<Book> rent(Long id, Long userId);
}
