package mk.ukim.finki.wp.eimt_lab.service.domain.impl;

import mk.ukim.finki.wp.eimt_lab.model.domain.*;
import mk.ukim.finki.wp.eimt_lab.repository.BookLendingRepository;
import mk.ukim.finki.wp.eimt_lab.repository.BookRepository;
import mk.ukim.finki.wp.eimt_lab.repository.UserRepository;
import mk.ukim.finki.wp.eimt_lab.repository.specifiation.BookSpecifications;
import mk.ukim.finki.wp.eimt_lab.service.domain.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookLendingRepository bookLendingRepository;

    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository, BookLendingRepository bookLendingRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.bookLendingRepository = bookLendingRepository;
    }


    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository
                .findById(id)
                .map((existingBook) -> {
                    existingBook.setName(book.getName());
                    existingBook.setCategory(book.getCategory());
                    existingBook.setState(book.getState());
                    existingBook.setRented(book.isRented());
                    existingBook.setAuthor(book.getAuthor());
                    return bookRepository.save(existingBook);
                });

    }

    @Override
    public Optional<Book> deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(bookRepository::delete);
        return book;

    }

    @Override
    public Page<Book> findAll(BookCategory category, BookState state, Long authorId, Boolean available, Pageable pageable) {
        Specification<Book> spec = BookSpecifications.filterBy(category, state, authorId, available);
        return bookRepository.findAll(spec, pageable);
    }
    @Override
    public Optional<Book> rent(Long id, Long userId) {
        return this.bookRepository.findById(id).map(book -> {
            if (book.getAvailableCopies() <= 0) {
                throw new RuntimeException("Nema dostapni kopii od knigata!");
            }

            User user = this.userRepository.findById(userId)
                    .orElseThrow();

            book.setAvailableCopies(book.getAvailableCopies() - 1);
            this.bookRepository.save(book);

            BookLending lending = new BookLending(user, book);
            this.bookLendingRepository.save(lending);

            return book;
        });
    }


}
