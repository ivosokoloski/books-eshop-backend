package mk.ukim.finki.wp.eimt_lab.service.application.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.eimt_lab.event.BookRentedEvent;
import mk.ukim.finki.wp.eimt_lab.model.domain.Author;
import mk.ukim.finki.wp.eimt_lab.model.domain.Book;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookCategory;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookState;
import mk.ukim.finki.wp.eimt_lab.model.dto.CreateBookDto;
import mk.ukim.finki.wp.eimt_lab.model.dto.DisplayBookDto;
import mk.ukim.finki.wp.eimt_lab.model.exception.AuthorNotFoundException;
import mk.ukim.finki.wp.eimt_lab.service.application.BookApplicationService;
import mk.ukim.finki.wp.eimt_lab.service.domain.AuthorService;
import mk.ukim.finki.wp.eimt_lab.service.domain.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final ApplicationEventPublisher eventPublisher;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService, ApplicationEventPublisher eventPublisher) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService
                .findById(id)
                .map(DisplayBookDto::from);

    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());


    }

    @Override
    public DisplayBookDto create(CreateBookDto createBookDto) {
        Author author = authorService
                .findById(createBookDto.authorId())
                .orElseThrow(() -> new AuthorNotFoundException(createBookDto.authorId()));
        return DisplayBookDto.from(bookService.create(createBookDto.toBook(author)));


    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        Author author = authorService
                .findById(createBookDto.authorId())
                .orElseThrow(() -> new AuthorNotFoundException(createBookDto.authorId()));
        return bookService
                .update(id, createBookDto.toBook(author))
                .map(DisplayBookDto::from);

    }

    @Override
    public Optional<DisplayBookDto> deleteById(Long id) {
        return bookService
                .deleteById(id)
                .map(DisplayBookDto::from);

    }

    @Override
    public Page<DisplayBookDto> findAll(BookCategory category, BookState state, Long authorId, Boolean available, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

        return bookService.findAll(category, state, authorId, available, pageable)
                .map(DisplayBookDto::from);
    }

    @Override
    @Transactional
    public void rentBook(Long id, Long userId) {
        Optional<Book> book = bookService.rent(id,userId);

        this.eventPublisher.publishEvent(new BookRentedEvent(book));
    }


}
