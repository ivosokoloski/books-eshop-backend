package mk.ukim.finki.wp.eimt_lab.repository;

import mk.ukim.finki.wp.eimt_lab.model.domain.Book;
import mk.ukim.finki.wp.eimt_lab.model.projection.BookExtendedProjection;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Page<BookExtendedProjection> findAllProjectedBy(Pageable pageable);
    @Override
    @EntityGraph(value = "book-author-country-graph", type = EntityGraph.EntityGraphType.FETCH)
    Page<Book> findAll(Specification<Book> spec, Pageable pageable);


    @Query(value = "SELECT b.* FROM books b " +
            "JOIN authors a ON b.author_id = a.id " +
            "JOIN countries c ON a.country_id = c.id " +
            "WHERE c.name = :countryName",
            nativeQuery = true)
    List<Book> findAllByCountryNative(@Param("countryName") String countryName);

    @Query(value = "SELECT * FROM books WHERE available_copies > :count",
            nativeQuery = true)
    List<Book> findAllWithMoreThanCopiesNative(@Param("count") Integer count);
}
