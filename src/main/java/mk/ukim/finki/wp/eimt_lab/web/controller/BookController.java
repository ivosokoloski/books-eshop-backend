package mk.ukim.finki.wp.eimt_lab.web.controller;

import jakarta.validation.Valid;
import mk.ukim.finki.wp.eimt_lab.model.domain.ActivityLog;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookCategory;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookState;
import mk.ukim.finki.wp.eimt_lab.model.dto.CreateBookDto;
import mk.ukim.finki.wp.eimt_lab.model.dto.DisplayBookDto;
import mk.ukim.finki.wp.eimt_lab.model.projection.BookExtendedProjection;
import mk.ukim.finki.wp.eimt_lab.repository.ActivityLogRepository;
import mk.ukim.finki.wp.eimt_lab.repository.BookRepository;
import mk.ukim.finki.wp.eimt_lab.service.application.BookApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookApplicationService bookApplicationService;
    private final BookRepository bookRepository;
    private final ActivityLogRepository activityLogRepository ;

    public BookController(BookApplicationService bookApplicationService, BookRepository bookRepository, ActivityLogRepository activityLogRepository) {
        this.bookApplicationService = bookApplicationService;
        this.bookRepository = bookRepository;
        this.activityLogRepository = activityLogRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DisplayBookDto>> findAll() {
        return ResponseEntity.ok(bookApplicationService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> create( @Valid @RequestBody CreateBookDto createBookDto) {
        return ResponseEntity.ok(bookApplicationService.create(createBookDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayBookDto> update(
            @PathVariable Long id,
            @RequestBody CreateBookDto createBookDto
    ) {
        return bookApplicationService
                .update(id, createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayBookDto> deleteById(@PathVariable Long id) {
        return bookApplicationService
                .deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<DisplayBookDto>> search(
            @RequestParam(required = false) BookCategory category,
            @RequestParam(required = false) BookState state,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Boolean available,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {

        return ResponseEntity.ok(bookApplicationService.findAll(category, state, authorId, available, page, size, sortBy));
    }
    @GetMapping("/projected")
    public ResponseEntity<Page<BookExtendedProjection>> getExtendedBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {


        Pageable pageable = PageRequest.of(page, size);

        Page<BookExtendedProjection> books = bookRepository.findAllProjectedBy(pageable);

        return ResponseEntity.ok(books);
    }
    @GetMapping("/by-country")
    public List<DisplayBookDto> getBooksByCountry(@RequestParam String countryName) {
        return bookRepository.findAllByCountryNative(countryName)
                .stream()
                .map(DisplayBookDto::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/more-than-copies")
    public List<DisplayBookDto> getBooksWithMoreCopies(@RequestParam Integer count) {
        return bookRepository.findAllWithMoreThanCopiesNative(count)
                .stream()
                .map(DisplayBookDto::from)
                .collect(Collectors.toList());
    }
    @PostMapping("/rent/{id}")
    public ResponseEntity<Void> rentBook(@PathVariable Long id, @RequestParam Long userId) {
        this.bookApplicationService.rentBook(id, userId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/logs")
    public Page<ActivityLog> getActivityLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return activityLogRepository.findAll(pageable);
    }

}
