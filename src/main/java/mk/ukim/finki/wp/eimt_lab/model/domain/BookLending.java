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
//@AllArgsConstructor
public class BookLending  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    public BookLending(User user, Book book) {
        this.user = user;
        this.book = book;
    }
}
