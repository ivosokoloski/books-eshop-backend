package mk.ukim.finki.wp.eimt_lab.model.projection;

import mk.ukim.finki.wp.eimt_lab.model.domain.BookCategory;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookState;
import org.springframework.beans.factory.annotation.Value;

public interface BookExtendedProjection {
    Long getId();
    String getName();
    BookCategory getCategory();
    BookState getState();
    Integer getAvailableCopies();


    @Value("#{target.author.name + ' ' + target.author.surname}")
    String getAuthorFullName();

    @Value("#{target.author.country.name}")
    String getCountryName();
}