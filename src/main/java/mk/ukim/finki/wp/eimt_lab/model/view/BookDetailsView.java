package mk.ukim.finki.wp.eimt_lab.model.view;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookCategory;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookState;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "book_details_view")
@Immutable
@Getter
public class BookDetailsView {
    @Id
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    @Enumerated(EnumType.STRING)
    private BookState state;

    @Column(name = "available_copies")
    private Integer availableCopies;

    @Column(name = "author_full_name")
    private String authorFullName;

    @Column(name = "country_name")
    private String countryName;
}