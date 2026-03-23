package mk.ukim.finki.wp.eimt_lab.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NamedEntityGraph(
        name = "book-author-country-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "author", subgraph = "author-country-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "author-country-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("country")
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book extends BaseAuditableEntity {
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    BookCategory category;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    BookState state;


    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    Author author;
    @Column(nullable = false)
    private boolean rented;
    @Column(name = "available_copies")
    @Min(value = 0, message = "Brojot na kopii ne moze da bide negativen")
    private Integer availableCopies;

}
