package mk.ukim.finki.wp.eimt_lab.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")

public class Author extends BaseAuditableEntity{
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String surname;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    Country country;
}
