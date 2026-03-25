package mk.ukim.finki.wp.eimt_lab.repository;

import mk.ukim.finki.wp.eimt_lab.model.domain.BookLending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BookLendingRepository extends JpaRepository<BookLending, Long> {

    @Query("SELECT b.name as name, COUNT(bl) as count FROM BookLending bl " +
            "JOIN bl.book b GROUP BY b.name ORDER BY COUNT(bl) DESC LIMIT 5")
    List<Map<String, Object>> findMostPopularBooks();

    @Query("SELECT a.name as name, a.surname as surname, COUNT(bl) as count " +
            "FROM BookLending bl JOIN bl.book b JOIN b.author a " +
            "GROUP BY a.name, a.surname ORDER BY COUNT(bl) DESC LIMIT 5")
    List<Map<String, Object>> findMostPopularAuthors();

    @Query("SELECT u.name as name, u.surname as surname, COUNT(bl) as count " +
            "FROM BookLending bl JOIN bl.user u " +
            "GROUP BY u.name, u.surname ORDER BY COUNT(bl) DESC LIMIT 5")
    List<Map<String, Object>> findTopUsers();
}
