package org.pehlivan.mert.bookservice.dto;

import lombok.*;
import org.pehlivan.mert.bookservice.model.Book;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateDto {
    private String title;
    private int bookYear;
    private String author;
    private String pressName;
    private String isbn;

    public Book toBook(BookCreateDto bookCreateDto) {
        return Book.builder()
                .title(bookCreateDto.getTitle())
                .bookYear(bookCreateDto.getBookYear())
                .author(bookCreateDto.getAuthor())
                .pressName(bookCreateDto.getPressName())
                .isbn(bookCreateDto.getIsbn())
                .build();
    }
}
