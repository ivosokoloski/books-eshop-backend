package mk.ukim.finki.wp.eimt_lab.web.controller;


import jakarta.validation.Valid;
import mk.ukim.finki.wp.eimt_lab.model.domain.ActivityLog;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookCategory;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookState;
import mk.ukim.finki.wp.eimt_lab.model.dto.CreateAuthorDto;
import mk.ukim.finki.wp.eimt_lab.model.dto.CreateBookDto;
import mk.ukim.finki.wp.eimt_lab.model.dto.DisplayAuthorDto;
import mk.ukim.finki.wp.eimt_lab.model.dto.DisplayBookDto;
import mk.ukim.finki.wp.eimt_lab.model.projection.BookExtendedProjection;
import mk.ukim.finki.wp.eimt_lab.repository.ActivityLogRepository;
import mk.ukim.finki.wp.eimt_lab.repository.AuthorRepository;
import mk.ukim.finki.wp.eimt_lab.repository.BookRepository;
import mk.ukim.finki.wp.eimt_lab.service.application.AuthorApplicationService;
import mk.ukim.finki.wp.eimt_lab.service.application.BookApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {
    private final AuthorApplicationService authorApplicationService;
    private final AuthorRepository authorRepository;
    private final ActivityLogRepository activityLogRepository ;

    public AuthorController(AuthorApplicationService authorApplicationService, AuthorRepository authorRepository, ActivityLogRepository activityLogRepository) {
        this.authorApplicationService = authorApplicationService;
        this.authorRepository = authorRepository;
        this.activityLogRepository = activityLogRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DisplayAuthorDto>> findAll() {
        return ResponseEntity.ok(authorApplicationService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayAuthorDto> create( @Valid @RequestBody CreateAuthorDto createAuthorDto) {
        return ResponseEntity.ok(authorApplicationService.create(createAuthorDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayAuthorDto> update(
            @PathVariable Long id,
            @RequestBody CreateAuthorDto createAuthorDto
    ) {
        return authorApplicationService
                .update(id, createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayAuthorDto> deleteById(@PathVariable Long id) {
        return authorApplicationService
                .deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @GetMapping("/logs")
    public Page<ActivityLog> getActivityLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return activityLogRepository.findAll(pageable);
    }

}
