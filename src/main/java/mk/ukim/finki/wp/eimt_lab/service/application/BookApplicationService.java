package mk.ukim.finki.wp.eimt_lab.service.application;

import mk.ukim.finki.wp.eimt_lab.model.domain.BookCategory;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookState;
import mk.ukim.finki.wp.eimt_lab.model.dto.CreateBookDto;
import mk.ukim.finki.wp.eimt_lab.model.dto.DisplayBookDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    Optional<DisplayBookDto> findById(Long id);

    List<DisplayBookDto> findAll();

    DisplayBookDto create(CreateBookDto createBookDto);

    Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto);

    Optional<DisplayBookDto> deleteById(Long id);
    Page<DisplayBookDto> findAll(BookCategory category, BookState state, Long authorId, Boolean available, int page, int size, String sortBy);
    void rentBook(Long bookId, Long userId);
}
