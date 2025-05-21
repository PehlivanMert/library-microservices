package org.pehlivan.mert.bookservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookIdDto {
    private Long bookId;
    private String isbn;

    public static BookIdDto convert(Long id, String isbn) {
        return new BookIdDto(id, isbn);
    }
}
