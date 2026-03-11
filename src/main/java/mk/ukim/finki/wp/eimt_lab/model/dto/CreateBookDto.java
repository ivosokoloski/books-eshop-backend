package mk.ukim.finki.wp.eimt_lab.model.dto;

import mk.ukim.finki.wp.eimt_lab.model.domain.Author;
import mk.ukim.finki.wp.eimt_lab.model.domain.Book;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookCategory;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookState;

public record CreateBookDto(
        String name,
        BookCategory category,
        BookState state,
        Long authorId,
        boolean rented
) {
    public Book toBook(Author author){
        return new Book(name,category,state,author,rented);
    }
}
