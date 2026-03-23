package mk.ukim.finki.wp.eimt_lab.web.controller;

import mk.ukim.finki.wp.eimt_lab.model.view.BookDetailsView;
import mk.ukim.finki.wp.eimt_lab.repository.BookDetailsViewRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book-details")
public class BookDetailsViewController {

    private final BookDetailsViewRepository bookDetailsViewRepository;

    public BookDetailsViewController(BookDetailsViewRepository bookDetailsViewRepository) {
        this.bookDetailsViewRepository = bookDetailsViewRepository;
    }


    @GetMapping
    public List<BookDetailsView> findAll() {
        return bookDetailsViewRepository.findAll();
    }


    @GetMapping("/search")
    public List<BookDetailsView> findByAuthor(@RequestParam String authorName) {
        return bookDetailsViewRepository.findAllByAuthorFullNameContaining(authorName);
    }
}