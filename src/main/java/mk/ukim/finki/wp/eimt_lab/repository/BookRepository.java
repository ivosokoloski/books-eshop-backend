package mk.ukim.finki.wp.eimt_lab.repository;

import mk.ukim.finki.wp.eimt_lab.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
