package mk.ukim.finki.wp.eimt_lab.repository;

import mk.ukim.finki.wp.eimt_lab.model.view.BookDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDetailsViewRepository extends JpaRepository<BookDetailsView, Long> {
    List<BookDetailsView> findAllByAuthorFullNameContaining(String name);
}