package mk.ukim.finki.wp.eimt_lab.model.dto;

import mk.ukim.finki.wp.eimt_lab.model.domain.Book;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookCategory;
import mk.ukim.finki.wp.eimt_lab.model.domain.BookState;

import java.util.List;

public record DisplayBookDto(
        Long id,
        String name,
        BookCategory category,
        BookState state,
        Long authorId,
        boolean rented
) {
    public static DisplayBookDto from(Book book){
        return  new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getState(),
                book.getAuthor().getId(),
                book.isRented()
        );
    }
    public static List<DisplayBookDto> from(List<Book> books){
        return books.stream().map(DisplayBookDto::from).toList();
    }

}
