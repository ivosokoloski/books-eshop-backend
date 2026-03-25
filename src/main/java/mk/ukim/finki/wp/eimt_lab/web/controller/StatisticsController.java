package mk.ukim.finki.wp.eimt_lab.web.controller;

import mk.ukim.finki.wp.eimt_lab.repository.BookLendingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final BookLendingRepository lendingRepository;

    public StatisticsController(BookLendingRepository lendingRepository) {
        this.lendingRepository = lendingRepository;
    }

    @GetMapping("/popular-books")
    public List<Map<String, Object>> getMostPopularBooks() {
        return lendingRepository.findMostPopularBooks();
    }

    @GetMapping("/popular-authors")
    public List<Map<String, Object>> getMostPopularAuthors() {
        return lendingRepository.findMostPopularAuthors();
    }

    @GetMapping("/top-users")
    public List<Map<String, Object>> getTopUsers() {
        return lendingRepository.findTopUsers();
    }
}