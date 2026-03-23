package mk.ukim.finki.wp.eimt_lab.repository.specifiation;

import jakarta.persistence.criteria.Predicate;
import mk.ukim.finki.wp.eimt_lab.model.domain.Book;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookCategory;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookState;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookSpecifications {
    public static Specification<Book> filterBy(BookCategory category, BookState state, Long authorId, Boolean available) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. Филтер по категорија
            if (category != null) {
                predicates.add(cb.equal(root.get("category"), category));
            }

            // 2. Филтер по состојба
            if (state != null) {
                predicates.add(cb.equal(root.get("state"), state));
            }

            // 3. Филтер по автор (одиме преку релацијата во Book ентитетот)
            if (authorId != null) {
                predicates.add(cb.equal(root.get("author").get("id"), authorId));
            }

            // 4. Филтер за достапност
            // Бидејќи рече дека немаш availableCopies, за почеток користиме !rented
            if (available != null) {
                predicates.add(cb.equal(root.get("rented"), !available));
            }

            // Ги спојуваме сите со "AND"
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
