package mk.ukim.finki.wp.eimt_lab.web.controller;


import jakarta.validation.Valid;
import mk.ukim.finki.wp.eimt_lab.model.domain.ActivityLog;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookCategory;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookState;
import mk.ukim.finki.wp.eimt_lab.model.dto.*;
import mk.ukim.finki.wp.eimt_lab.model.projection.BookExtendedProjection;
import mk.ukim.finki.wp.eimt_lab.repository.ActivityLogRepository;
import mk.ukim.finki.wp.eimt_lab.repository.AuthorRepository;
import mk.ukim.finki.wp.eimt_lab.repository.BookRepository;
import mk.ukim.finki.wp.eimt_lab.repository.CountryRepository;
import mk.ukim.finki.wp.eimt_lab.service.application.AuthorApplicationService;
import mk.ukim.finki.wp.eimt_lab.service.application.BookApplicationService;
import mk.ukim.finki.wp.eimt_lab.service.application.CountryApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = "http://localhost:3000")
public class CountryController {
    private final CountryApplicationService countryApplicationService;
    private final CountryRepository countryRepository;
    private final ActivityLogRepository activityLogRepository ;

    public CountryController(CountryApplicationService countryApplicationService, CountryRepository countryRepository, ActivityLogRepository activityLogRepository) {
        this.countryApplicationService = countryApplicationService;
        this.countryRepository = countryRepository;
        this.activityLogRepository = activityLogRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DisplayCountryDto>> findAll() {
        return ResponseEntity.ok(countryApplicationService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> create( @Valid @RequestBody CreateCountryDto createCountryDto) {
        return ResponseEntity.ok(countryApplicationService.create(createCountryDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayCountryDto> update(
            @PathVariable Long id,
            @RequestBody CreateCountryDto createCountryDto
    ) {
        return countryApplicationService
                .update(id, createCountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayCountryDto> deleteById(@PathVariable Long id) {
        return countryApplicationService
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
