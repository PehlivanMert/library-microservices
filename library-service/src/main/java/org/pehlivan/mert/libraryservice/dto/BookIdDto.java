package org.pehlivan.mert.libraryservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookIdDto {
    private String bookId;
    private String isbn;

    public static BookIdDto convert(String id, String isbn) {
        return new BookIdDto(id, isbn);
    }
}
