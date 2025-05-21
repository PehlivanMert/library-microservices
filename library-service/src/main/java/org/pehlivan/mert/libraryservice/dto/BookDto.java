package org.pehlivan.mert.libraryservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private BookIdDto id;
    private String title;
    private int bookYear;
    private String author;
    private String pressName;

}
