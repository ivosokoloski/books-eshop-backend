package mk.ukim.finki.wp.eimt_lab.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book extends BaseAuditableEntity {
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    BookCategory category;
    @Column(nullable = false)
    BookState state;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    Author author;
    @Column(nullable = false)
    private boolean rented;

}
